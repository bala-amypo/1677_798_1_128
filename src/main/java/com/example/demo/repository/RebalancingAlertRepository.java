package com.example.demo.repository;

import com.example.demo.entity.RebalancingAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RebalancingAlertRepository extends JpaRepository<RebalancingAlert, Long> {
    List<RebalancingAlert> findByInvestorId(Long investorId);
    List<RebalancingAlert> findByInvestorIdAndResolved(Long investorId, boolean resolved);
    List<RebalancingAlert> findByResolved(boolean resolved);
    List<RebalancingAlert> findByAlertDateAfter(LocalDate date);
}