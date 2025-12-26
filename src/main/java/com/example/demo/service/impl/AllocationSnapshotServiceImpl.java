package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {
    private final AllocationSnapshotRecordRepository snapRepo;
    private final HoldingRecordRepository holdRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(AllocationSnapshotRecordRepository snapRepo,
                                         HoldingRecordRepository holdRepo,
                                         AssetClassAllocationRuleRepository ruleRepo,
                                         RebalancingAlertRecordRepository alertRepo) {
        this.snapRepo = snapRepo;
        this.holdRepo = holdRepo;
        this.ruleRepo = ruleRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdRepo.findByInvestorId(investorId);
        if (holdings.isEmpty()) throw new IllegalArgumentException("No holdings found");

        double totalValue = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();
        List<AssetClassAllocationRule> rules = ruleRepo.findByInvestorIdAndActiveTrue(investorId);

        for (AssetClassAllocationRule rule : rules) {
            double currentAssetValue = holdings.stream()
                    .filter(h -> h.getAssetClass() == rule.getAssetClass())
                    .mapToDouble(HoldingRecord::getCurrentValue).sum();
            double currentPct = (currentAssetValue / totalValue) * 100;

            if (currentPct > rule.getTargetPercentage()) {
                // Corrected to 9-argument constructor based on your error log
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                    null, investorId, rule.getAssetClass(), currentPct, rule.getTargetPercentage(),
                    AlertSeverity.MEDIUM, "Rebalancing required", LocalDateTime.now(), false
                );
                alertRepo.save(alert);
            }
        }

        // Corrected to 5-argument constructor based on your error log
        AllocationSnapshotRecord snap = new AllocationSnapshotRecord(null, investorId, LocalDateTime.now(), totalValue, "{}");
        return snapRepo.save(snap);
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Snapshot not found"));
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapRepo.findAll();
    }
}