package com.example.demo.Impl;

import com.example.demo.entity.AllocationSnapshot;
import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.entity.InvestorProfile;
import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RebalancingAlertServiceImpl implements RebalancingAlertService {
    
    @Autowired
    private RebalancingAlertRecordRepository alertRepository;
    
    @Autowired
    private AllocationRuleServiceImpl allocationRuleService;
    
    @Autowired
    private AllocationSnapshotServiceImpl snapshotService;
    
    @Autowired
    private InvestorProfilesServiceImpl investorProfileService;
    
    @Override
    public List<RebalancingAlertRecord> checkForRebalancingAlerts(Long investorProfileId) {
        List<RebalancingAlertRecord> alerts = new ArrayList<>();
        InvestorProfile profile = investorProfileService.getInvestorProfileById(investorProfileId);
        
        if (profile == null) {
            return alerts;
        }
        
        AllocationSnapshot snapshot = snapshotService.createSnapshot(investorProfileId);
        Map<String, Double> allocations = snapshot.getAssetAllocations();
        List<AssetClassAllocationRule> activeRules = allocationRuleService.getActiveAllocationRules(investorProfileId);
        
        for (AssetClassAllocationRule rule : activeRules) {
            AssetClassType assetClass = rule.getAssetClass();
            Double targetPercentage = rule.getTargetPercentage();
            Double tolerancePercentage = rule.getTolerancePercentage();
            Double currentPercentage = allocations.get(assetClass.name());
            
            if (currentPercentage != null && currentPercentage > (targetPercentage + tolerancePercentage)) {
                Double driftAmount = currentPercentage - targetPercentage;
                AlertSeverity severity = determineSeverity(driftAmount);
                
                RebalancingAlertRecord alert = new RebalancingAlertRecord();
                alert.setAssetClass(assetClass);
                alert.setSeverity(severity);
                alert.setMessage(String.format("%s allocation exceeds target by %.2f%%", 
                    assetClass.name(), driftAmount));
                alert.setCurrentPercentage(currentPercentage);
                alert.setTargetPercentage(targetPercentage);
                alert.setDriftAmount(driftAmount);
                alert.setAlertTimestamp(LocalDateTime.now());
                alert.setInvestorProfile(profile);
                alert.setAllocationSnapshot(snapshot);
                
                alertRepository.save(alert);
                alerts.add(alert);
            }
        }
        
        return alerts;
    }
    
    private AlertSeverity determineSeverity(Double driftAmount) {
        if (driftAmount >= 10.0) return AlertSeverity.CRITICAL;
        if (driftAmount >= 5.0) return AlertSeverity.HIGH;
        if (driftAmount >= 2.0) return AlertSeverity.MEDIUM;
        return AlertSeverity.LOW;
    }
    
    @Override
    public List<RebalancingAlertRecord> getAlertsByInvestorProfile(Long investorProfileId) {
        return alertRepository.findByInvestorProfileId(investorProfileId);
    }
    
    @Override
    public List<RebalancingAlertRecord> getUnresolvedAlerts() {
        return alertRepository.findByIsResolved(false);
    }
    
    @Override
    public List<RebalancingAlertRecord> getAlertsBySeverity(String severity) {
        return alertRepository.findBySeverityAndIsResolved(AlertSeverity.valueOf(severity.toUpperCase()), false);
    }
    
    @Override
    public RebalancingAlertRecord resolveAlert(Long alertId) {
        RebalancingAlertRecord alert = alertRepository.findById(alertId).orElse(null);
        if (alert != null) {
            alert.setIsResolved(true);
            return alertRepository.save(alert);
        }
        return null;
    }
    
    @Override
    public void deleteAlert(Long alertId) {
        alertRepository.deleteById(alertId);
    }
}