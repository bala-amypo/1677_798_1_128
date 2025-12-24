package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {
    
    private final AllocationSnapshotRecordRepository snapshotRepository;
    private final HoldingRecordRepository holdingRepository;
    private final AssetClassAllocationRuleRepository ruleRepository;
    private final RebalancingAlertRecordRepository alertRepository;
    private final InvestorProfileRepository investorRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    // Constructor Injection as required
    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRepository,
            HoldingRecordRepository holdingRepository,
            AssetClassAllocationRuleRepository ruleRepository,
            RebalancingAlertRecordRepository alertRepository,
            InvestorProfileRepository investorRepository) {
        this.snapshotRepository = snapshotRepository;
        this.holdingRepository = holdingRepository;
        this.ruleRepository = ruleRepository;
        this.alertRepository = alertRepository;
        this.investorRepository = investorRepository;
    }
    
    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        // Check if investor exists
        investorRepository.findById(investorId)
            .orElseThrow(() -> new IllegalArgumentException("Investor not found"));
        
        // Fetch holdings
        List<HoldingRecord> holdings = holdingRepository.findByInvestorId(investorId);
        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings found for investor");
        }
        
        // Calculate total portfolio value
        double totalValue = holdings.stream()
            .mapToDouble(HoldingRecord::getCurrentValue)
            .sum();
        
        if (totalValue <= 0) {
            throw new IllegalArgumentException("Total portfolio value must be > 0");
        }
        
        // Calculate percentages per asset class
        Map<AssetClassType, Double> percentages = new HashMap<>();
        Map<AssetClassType, Double> values = new HashMap<>();
        
        for (AssetClassType assetClass : AssetClassType.values()) {
            double assetValue = holdings.stream()
                .filter(h -> h.getAssetClass() == assetClass)
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();
            
            values.put(assetClass, assetValue);
            percentages.put(assetClass, (assetValue / totalValue) * 100);
        }
        
        // Fetch active rules
        List<AssetClassAllocationRule> activeRules = ruleRepository.findActiveRulesHql(investorId);
        
        // Create and save snapshot
        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord();
        snapshot.setInvestorId(investorId);
        snapshot.setSnapshotDate(LocalDateTime.now());
        snapshot.setTotalPortfolioValue(totalValue);
        
        try {
            Map<String, Object> allocationData = new HashMap<>();
            allocationData.put("percentages", percentages);
            allocationData.put("values", values);
            allocationData.put("totalValue", totalValue);
            snapshot.setAllocationJson(objectMapper.writeValueAsString(allocationData));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize allocation data", e);
        }
        
        AllocationSnapshotRecord savedSnapshot = snapshotRepository.save(snapshot);
        
        // Check for alerts
        checkAndCreateAlerts(investorId, percentages, activeRules);
        
        return savedSnapshot;
    }
    
    private void checkAndCreateAlerts(Long investorId, 
                                     Map<AssetClassType, Double> percentages,
                                     List<AssetClassAllocationRule> rules) {
        for (AssetClassAllocationRule rule : rules) {
            Double currentPercentage = percentages.get(rule.getAssetClass());
            if (currentPercentage == null) continue;
            
            if (currentPercentage > rule.getTargetPercentage()) {
                // Validation as required
                if (currentPercentage <= rule.getTargetPercentage()) {
                    throw new IllegalArgumentException("Alert logic validation failed: currentPercentage must be > targetPercentage");
                }
                
                RebalancingAlertRecord alert = new RebalancingAlertRecord();
                alert.setInvestorId(investorId);
                alert.setAssetClass(rule.getAssetClass());
                alert.setCurrentPercentage(currentPercentage);
                alert.setTargetPercentage(rule.getTargetPercentage());
                alert.setSeverity(calculateSeverity(currentPercentage, rule.getTargetPercentage()));
                alert.setMessage(String.format(
                    "Asset class %s exceeds target allocation. Current: %.2f%%, Target: %.2f%%",
                    rule.getAssetClass(), currentPercentage, rule.getTargetPercentage()
                ));
                alert.setAlertDate(LocalDateTime.now());
                alert.setResolved(false);
                
                alertRepository.save(alert);
            }
        }
    }
    
    private AlertSeverity calculateSeverity(double current, double target) {
        double deviation = ((current - target) / target) * 100;
        
        if (deviation > 50) return AlertSeverity.HIGH;
        if (deviation > 20) return AlertSeverity.MEDIUM;
        return AlertSeverity.LOW;
    }
    
    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Snapshot not found"));
    }
    
    @Override
    public List<AllocationSnapshotRecord> getSnapshotsByInvestor(Long investorId) {
        return snapshotRepository.findByInvestorId(investorId);
    }
    
    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepository.findAll();
    }
}