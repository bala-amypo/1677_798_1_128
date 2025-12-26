package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AllocationSnapshotServiceImpl {
    private final AllocationSnapshotRecordRepository snapshotRepo;
    private final HoldingRecordRepository holdingRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(AllocationSnapshotRecordRepository s, HoldingRecordRepository h, 
                                        AssetClassAllocationRuleRepository r, RebalancingAlertRecordRepository a) {
        this.snapshotRepo = s; this.holdingRepo = h; this.ruleRepo = r; this.alertRepo = a;
    }

    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
        if (holdings.isEmpty()) throw new IllegalArgumentException("No holdings for investor");
        double total = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();
        AllocationSnapshotRecord snap = new AllocationSnapshotRecord(investorId, LocalDateTime.now(), total, "{}");
        return snapshotRepo.save(snap);
    }

    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID: " + id));
    }
    public List<AllocationSnapshotRecord> getAllSnapshots() { return snapshotRepo.findAll(); }
}