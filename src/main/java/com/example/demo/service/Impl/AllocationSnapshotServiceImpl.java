package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final HoldingRecordRepository repository;

    public AllocationSnapshotServiceImpl(HoldingRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Double> calculateAllocation(Long investorId) {

        List<HoldingRecord> holdings = repository.findByInvestorId(investorId);

        Map<String, Double> allocation = new HashMap<>();

        for (HoldingRecord h : holdings) {
            allocation.merge(
                    h.getAssetClass(),
                    h.getCurrentValue(),
                    Double::sum
            );
        }

        return allocation;
    }
}
