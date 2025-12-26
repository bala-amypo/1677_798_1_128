package com.example.demo.service.impl;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.repository.AllocationSnapshotRecordRepository;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

@Service
public class AllocationSnapshotServiceImpl
        implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository repository;

    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public AllocationSnapshotRecord createSnapshot(Long investorId) {
        AllocationSnapshotRecord record = new AllocationSnapshotRecord();
        record.setInvestorId(investorId);
        return repository.save(record);
    }
}
