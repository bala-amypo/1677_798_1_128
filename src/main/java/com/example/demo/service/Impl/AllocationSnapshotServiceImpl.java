package com.example.demo.service.impl;

import com.example.demo.entity.AllocationSnapshot;
import com.example.demo.repository.AllocationSnapshotRepository;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRepository snapshotRepository;

    public AllocationSnapshotServiceImpl(AllocationSnapshotRepository snapshotRepository) {
        this.snapshotRepository = snapshotRepository;
    }

    @Override
    public AllocationSnapshot createSnapshot(Long investorId) {
        AllocationSnapshot snapshot = new AllocationSnapshot();
        snapshot.setInvestorId(investorId);
        snapshot.setSnapshotDate(LocalDate.now());
        snapshot.setTotalValue(0.0);
        snapshot.setSnapshotData("{}");
        
        return snapshotRepository.save(snapshot);
    }

    @Override
    public AllocationSnapshot getSnapshotById(Long id) {
        return snapshotRepository.findById(id).orElse(null);
    }

    @Override
    public List<AllocationSnapshot> getSnapshotsByInvestor(Long investorId, LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            return snapshotRepository.findByInvestorIdAndSnapshotDateBetween(investorId, startDate, endDate);
        } else {
            return snapshotRepository.findByInvestorId(investorId);
        }
    }

    @Override
    public List<AllocationSnapshot> getAllSnapshots() {
        return snapshotRepository.findAll();
    }
}