package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.*;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            RebalancingAlertRecordRepository alertRepo
    ) {
        this.snapshotRepo = snapshotRepo;
        this.holdingRepo = holdingRepo;
        this.ruleRepo = ruleRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
        if (holdings.isEmpty()) throw new RuntimeException("No holdings");

        double total = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();
        if (total <= 0) throw new RuntimeException("must be > 0");

        Map<AssetClassType, Double> allocation = new EnumMap<>(AssetClassType.class);
        holdings.forEach(h ->
                allocation.merge(h.getAssetClass(), h.getCurrentValue(), Double::sum)
        );

        Map<AssetClassType, Double> percentages = new EnumMap<>(AssetClassType.class);
        allocation.forEach((k, v) -> percentages.put(k, (v / total) * 100));

        ruleRepo.findActiveRulesHql(investorId).forEach(rule -> {
            double current = percentages.getOrDefault(rule.getAssetClass(), 0.0);
            if (current > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord();
                alert.setInvestorId(investorId);
                alert.setAssetClass(rule.getAssetClass());
                alert.setCurrentPercentage(current);
                alert.setTargetPercentage(rule.getTargetPercentage());
                alert.setSeverity(AlertSeverity.HIGH);
                alert.setMessage("currentPercentage > targetPercentage");
                alertRepo.save(alert);
            }
        });

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord();
        snapshot.setInvestorId(investorId);
        snapshot.setTotalPortfolioValue(total);

        try {
            snapshot.setAllocationJson(new ObjectMapper().writeValueAsString(percentages));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return snapshotRepo.save(snapshot);
    }

    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<AllocationSnapshotRecord> getSnapshotsByInvestor(Long investorId) {
        return snapshotRepo.findByInvestorId(investorId);
    }

    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepo.findAll();
    }
}
