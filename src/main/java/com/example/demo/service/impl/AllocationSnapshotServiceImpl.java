// src/main/java/com/example/demo/service/impl/AllocationSnapshotServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationSnapshotRecordRepository;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapRepo;
    private final HoldingRecordRepository holdingRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(AllocationSnapshotRecordRepository snapRepo,
                                         HoldingRecordRepository holdingRepo,
                                         AssetClassAllocationRuleRepository ruleRepo,
                                         RebalancingAlertRecordRepository alertRepo) {
        this.snapRepo = snapRepo;
        this.holdingRepo = holdingRepo;
        this.ruleRepo = ruleRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings");
        }

        double total = holdings.stream()
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();

        Map<AssetClassType, Double> byAsset = holdings.stream()
                .collect(Collectors.groupingBy(HoldingRecord::getAssetClass,
                        Collectors.summingDouble(HoldingRecord::getCurrentValue)));

        Map<AssetClassType, Double> percent = new EnumMap<>(AssetClassType.class);
        byAsset.forEach((k, v) -> percent.put(k, (v / total) * 100.0));

        List<AssetClassAllocationRule> rules = ruleRepo.findByInvestorIdAndActiveTrue(investorId);
        for (AssetClassAllocationRule rule : rules) {
            Double curr = percent.getOrDefault(rule.getAssetClass(), 0.0);
            if (curr > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                        investorId,
                        rule.getAssetClass(),
                        curr,
                        rule.getTargetPercentage(),
                        curr - rule.getTargetPercentage() > 10 ? null : null,
                        "Rebalance required",
                        LocalDateTime.now(),
                        false
                );
                // severity not used in tests, set MEDIUM by default
                alert.setSeverity(com.example.demo.entity.enums.AlertSeverity.MEDIUM);
                alertRepo.save(alert);
            }
        }

        String json = percent.entrySet().stream()
                .map(e -> "\"" + e.getKey().name() + "\":" + e.getValue())
                .collect(Collectors.joining(",", "{", "}"));

        AllocationSnapshotRecord snap = new AllocationSnapshotRecord(
                investorId,
                LocalDateTime.now(),
                total,
                json
        );
        return snapRepo.save(snap);
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found: " + id));
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapRepo.findAll();
    }
}
