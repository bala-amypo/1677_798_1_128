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

    public AllocationSnapshotServiceImpl(AllocationSnapshotRecordRepository snapshotRepo, 
                                        HoldingRecordRepository holdingRecordRepository,
                                        AssetClassAllocationRuleRepository assetClassAllocationRuleRepository, 
                                        RebalancingAlertRecordRepository rebalancingAlertRecordRepository) {
        this.snapshotRepo = snapshotRepo;
        this.holdingRepo = holdingRecordRepository;
        this.ruleRepo = assetClassAllocationRuleRepository;
        this.alertRepo = rebalancingAlertRecordRepository;
    }

    /**
     * Requirement for Priority 67 & 68
     */
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
        
        // TEST REQUIREMENT (Priority 68): Must throw IllegalArgumentException with "No holdings"
        if (holdings == null || holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings found for investor: " + investorId);
        }

        // Calculate total portfolio value
        double totalPortfolioValue = holdings.stream()
                .mapToDouble(h -> h.getCurrentValue())
                .sum();

        // Save and return snapshot
        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord(
                investorId, 
                LocalDateTime.now(), 
                totalPortfolioValue, 
                "{}" // Default empty JSON for allocation details
        );
        
        return snapshotRepo.save(snapshot);
    }

    /**
     * Requirement for Priority 69
     */
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot not found for ID: " + id));
    }

    /**
     * Requirement for Priority 70
     */
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepo.findAll();
    }
}