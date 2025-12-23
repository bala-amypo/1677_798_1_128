package com.example.demo.repository;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.entity.enums.AlertSeverity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RebalancingAlertRecordRepository extends JpaRepository<RebalancingAlertRecord, Long> {
    List<RebalancingAlertRecord> findByInvestorProfileId(Long investorProfileId);
    List<RebalancingAlertRecord> findByIsResolved(Boolean isResolved);
    List<RebalancingAlertRecord> findBySeverityAndIsResolved(AlertSeverity severity, Boolean isResolved);
    List<RebalancingAlertRecord> findByAlertTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<RebalancingAlertRecord> findByInvestorProfileIdAndIsResolved(Long investorProfileId, Boolean isResolved);
}