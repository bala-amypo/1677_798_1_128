// src/main/java/com/example/demo/service/impl/AllocationSnapshotServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapshotRepo;
    private final HoldingRecordRepository holdingRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(AllocationSnapshotRecordRepository snapshotRepo,
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
            throw new IllegalArgumentException("No holdings for investor " + investorId);
        }

        double total = holdings.stream()
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();

        Map<AssetClassType, Double> actualPct = holdings.stream()
                .collect(Collectors.groupingBy(HoldingRecord::getAssetClass,
                        Collectors.summingDouble(HoldingRecord::getCurrentValue)));

        actualPct.replaceAll((k, v) -> (v / total) * 100.0);

        List<AssetClassAllocationRule> rules =
                ruleRepo.findByInvestorIdAndActiveTrue(investorId);

        for (AssetClassAllocationRule rule : rules) {
            double act = actualPct.getOrDefault(rule.getAssetClass(), 0.0);
            if (act > rule.getTargetPercentage()) {
                AlertSeverity severity = AlertSeverity.MEDIUM;
                double diff = act - rule.getTargetPercentage();
                if (diff > 15) {
                    severity = AlertSeverity.HIGH;
                } else if (diff < 5) {
                    severity = AlertSeverity.LOW;
                }
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                        investorId, rule.getAssetClass(), act, rule.getTargetPercentage(),
                        severity, "Rebalancing required", LocalDateTime.now(), false
                );
                alertRepo.save(alert);
            }
        }

        String json = actualPct.toString();
        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord(
                investorId, LocalDateTime.now(), total, json
        );
        return snapshotRepo.save(snapshot);
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot not found: " + id));
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepo.findAll();
    }
}
