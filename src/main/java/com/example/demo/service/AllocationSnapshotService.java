package com.example.demo.Impl;

import com.example.demo.entity.AllocationSnapshot;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AllocationSnapshotService {
    AllocationSnapshot createSnapshot(Long investorProfileId);
    AllocationSnapshot getSnapshotById(Long id);
    List<AllocationSnapshot> getSnapshotsByInvestorProfile(Long investorProfileId);
    List<AllocationSnapshot> getSnapshotsByTimeRange(LocalDateTime start, LocalDateTime end);
    Map<String, Double> calculateAssetAllocations(Long investorProfileId);
    void deleteSnapshot(Long id);
}