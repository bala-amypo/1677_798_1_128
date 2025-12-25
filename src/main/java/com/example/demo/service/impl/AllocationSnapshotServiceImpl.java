package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {
    private final AllocationSnapshotRecordRepository snapshotRepo;
    private final HoldingRecordRepository holdingRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRepo,
            HoldingRecordRepository holdingRepo,
            AssetClassAllocationRuleRepository ruleRepo,
            RebalancingAlertRecordRepository alertRepo) {
        this.snapshotRepo = snapshotRepo;
        this.holdingRepo = holdingRepo;
        this.ruleRepo = ruleRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
        if (holdings.isEmpty()) throw new RuntimeException("not found");

        double totalValue = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();
        if (totalValue <= 0) throw new RuntimeException("must be > 0");

        List<AssetClassAllocationRule> rules = ruleRepo.findActiveRulesHql(investorId);
        Map<String, Double> allocations = new HashMap<>();

        for (HoldingRecord holding : holdings) {
            double currentPercentage = (holding.getCurrentValue() / totalValue) * 100;
            allocations.put(holding.getAssetClass().name(), currentPercentage);

            rules.stream()
                .filter(r -> r.getAssetClass() == holding.getAssetClass())
                .findFirst()
                .ifPresent(rule -> {
                    if (currentPercentage > rule.getTargetPercentage()) {
                        RebalancingAlertRecord alert = new RebalancingAlertRecord();
                        alert.setInvestorId(investorId);
                        alert.setAssetClass(holding.getAssetClass());
                        alert.setCurrentPercentage(currentPercentage);
                        alert.setTargetPercentage(rule.getTargetPercentage());
                        alert.setSeverity(AlertSeverity.HIGH);
                        alert.setMessage("currentPercentage > targetPercentage breach detected");
                        alertRepo.save(alert);
                    }
                });
        }

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord();
        snapshot.setInvestorId(investorId);
        snapshot.setTotalPortfolioValue(totalValue);
        snapshot.setAllocationJson(allocations.toString());
        return snapshotRepo.save(snapshot);
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepo.findAll();
    }
}