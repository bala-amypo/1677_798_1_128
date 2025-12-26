package com.example.demo.service.impl;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationSnapshotRecordRepository;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.repository.RebalancingAlertRecordRepository;
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
        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings found");
        }

        double totalValue = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();
        List<AssetClassAllocationRule> rules = ruleRepo.findByInvestorIdAndActiveTrue(investorId);

        for (AssetClassAllocationRule rule : rules) {
            double currentAssetValue = holdings.stream()
                    .filter(h -> h.getAssetClass() == rule.getAssetClass())
                    .mapToDouble(HoldingRecord::getCurrentValue).sum();
            
            double currentPct = (totalValue > 0) ? (currentAssetValue / totalValue) * 100 : 0;

            if (currentPct > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                    null, investorId, rule.getAssetClass(), currentPct, rule.getTargetPercentage(),
                    AlertSeverity.MEDIUM, "Threshold exceeded", LocalDateTime.now(), false
                );
                alertRepo.save(alert);
            }
        }

        AllocationSnapshotRecord snap = new AllocationSnapshotRecord(null, investorId, LocalDateTime.now(), totalValue, "{}");
        return snapRepo.save(snap);
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapRepo.findAll();
    }
}