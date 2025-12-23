package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.*;
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
        if (holdings.isEmpty()) {
            throw new RuntimeException("No holdings");
        }

        double total = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();

        Map<AssetClassType, Double> allocation = new EnumMap<>(AssetClassType.class);
        for (HoldingRecord h : holdings) {
            allocation.merge(h.getAssetClass(), h.getCurrentValue(), Double::sum);
        }

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord();
        snapshot.setInvestorId(investorId);
        snapshot.setTotalPortfolioValue(total);
        snapshot.setAllocationJson(allocation.toString());

        snapshotRepo.save(snapshot);

        for (AssetClassAllocationRule rule : ruleRepo.findActiveRulesHql(investorId)) {
            double value = allocation.getOrDefault(rule.getAssetClass(), 0.0);
            double pct = (value / total) * 100;

            if (pct > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord();
                alert.setInvestorId(investorId);
                alert.setAssetClass(rule.getAssetClass());
                alert.setCurrentPercentage(pct);
                alert.setTargetPercentage(rule.getTargetPercentage());
                alert.setSeverity(AlertSeverity.HIGH);
                alert.setMessage("Asset exceeded target allocation");

                alert.validate();
                alertRepo.save(alert);
            }
        }

        return snapshot;
    }
}
