package com.example.demo.service.impl;

import com.example.demo.repository.AllocationSnapshotRecordRepository;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class AllocationSnapshotServiceImpl {

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

    // Existing service methods...
}
