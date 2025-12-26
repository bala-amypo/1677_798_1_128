package com.example.demo.service.impl;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationSnapshotRecordRepository;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository allocationSnapshotRecordRepository;
    private final HoldingRecordRepository holdingRecordRepository;
    private final AssetClassAllocationRuleRepository allocationRuleRepository;
    private final RebalancingAlertRecordRepository alertRepository;

    // Updated constructor for 4 repositories
    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository allocationSnapshotRecordRepository,
            HoldingRecordRepository holdingRecordRepository,
            AssetClassAllocationRuleRepository allocationRuleRepository,
            RebalancingAlertRecordRepository alertRepository) {

        this.allocationSnapshotRecordRepository = allocationSnapshotRecordRepository;
        this.holdingRecordRepository = holdingRecordRepository;
        this.allocationRuleRepository = allocationRuleRepository;
        this.alertRepository = alertRepository;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        AllocationSnapshotRecord record = new AllocationSnapshotRecord();
        record.setInvestorId(investorId);
        // TODO: implement actual snapshot computation using holdings and allocation rules
        return allocationSnapshotRecordRepository.save(record);
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return allocationSnapshotRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot not found: " + id));
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return allocationSnapshotRecordRepository.findAll();
    }
}
