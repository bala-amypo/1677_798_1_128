package com.example.demo.service;

import com.example.demo.entity.AllocationSnapshot;
import java.time.LocalDate;
import java.util.List;

public interface AllocationSnapshotService {
    AllocationSnapshot createSnapshot(Long investorId);
    AllocationSnapshot getSnapshotById(Long id);
    List<AllocationSnapshot> getSnapshotsByInvestor(Long investorId, LocalDate startDate, LocalDate endDate);
    List<AllocationSnapshot> getAllSnapshots();
}