package com.example.demo.repository;

import com.example.demo.entity.AllocationSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AllocationSnapshotRepository extends JpaRepository<AllocationSnapshot, Long> {
    List<AllocationSnapshot> findByInvestorProfileId(Long investorProfileId);
    List<AllocationSnapshot> findByUserId(Long userId);
    List<AllocationSnapshot> findBySnapshotTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<AllocationSnapshot> findByInvestorProfileIdAndSnapshotTimestampBetween(Long investorProfileId, LocalDateTime start, LocalDateTime end);
}