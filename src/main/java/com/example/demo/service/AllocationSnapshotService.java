package com.example.demo.service;

import com.example.demo.entity.AllocationSnapshot;

import java.time.LocalDate;
import java.util.List;

public interface AllocationSnapshotService {
    AllocationSnapshot createSnapshot(Long investorId);
    AllocationSnapshot getSnapshot(Long snapshotId);
    List<AllocationSnapshot> getSnapshotsByInvestor(Long investorId, LocalDate startDate, LocalDate endDate);
    AllocationSnapshot getLatestSnapshot(Long investorId);
    void deleteSnapshot(Long snapshotId);
}