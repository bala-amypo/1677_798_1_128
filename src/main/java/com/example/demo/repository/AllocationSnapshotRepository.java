package com.example.demo.repository;

import com.example.demo.entity.AllocationSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AllocationSnapshotRepository extends JpaRepository<AllocationSnapshot, Long> {
    List<AllocationSnapshot> findByInvestorId(Long investorId);
    List<AllocationSnapshot> findByInvestorIdAndSnapshotDateBetween(Long investorId, LocalDate startDate, LocalDate endDate);
}