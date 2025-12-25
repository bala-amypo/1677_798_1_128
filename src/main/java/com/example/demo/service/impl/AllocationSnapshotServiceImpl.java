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

    // CONSTRUCTOR INJECTION - STRICT ORDER FOR TEST SUITE
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
        if (holdings.isEmpty()) throw new RuntimeException("No holdings not found");

        double total = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();
        if (total <= 0) throw new RuntimeException("totalPortfolioValue must be > 0");

        Map<String, Double> allocations = new HashMap<>();
        List<AssetClassAllocationRule> rules = ruleRepo.findActiveRulesHql(investorId);

        for (HoldingRecord h : holdings) {
            double currentPct = (h.getCurrentValue() / total) * 100;
            allocations.put(h.getAssetClass().name(), currentPct);

            rules.stream()
                .filter(r -> r.getAssetClass() == h.getAssetClass())
                .findFirst()
                .ifPresent(r -> {
                    if (currentPct > r.getTargetPercentage()) {
                        RebalancingAlertRecord alert = new RebalancingAlertRecord();
                        alert.setInvestorId(investorId);
                        alert.setAssetClass(h.getAssetClass());
                        alert.setCurrentPercentage(currentPct);
                        alert.setTargetPercentage(r.getTargetPercentage());
                        alert.setSeverity(AlertSeverity.HIGH);
                        alert.setMessage("currentPercentage > targetPercentage breach");
                        alert.setResolved(false);
                        alertRepo.save(alert);
                    }
                });
        }

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord();
        snapshot.setInvestorId(investorId);
        snapshot.setTotalPortfolioValue(total);
        snapshot.setAllocationJson(allocations.toString());
        return snapshotRepo.save(snapshot);
    }

    @Override public Optional<AllocationSnapshotRecord> getSnapshotById(Long id) { return snapshotRepo.findById(id); }
    @Override public List<AllocationSnapshotRecord> getAllSnapshots() { return snapshotRepo.findAll(); }
}