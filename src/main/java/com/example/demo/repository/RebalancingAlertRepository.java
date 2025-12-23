package com.example.demo.repository;

import com.example.demo.entity.RebalancingAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RebalancingAlertRepository extends JpaRepository<RebalancingAlert, Long> {
    List<RebalancingAlert> findByUserId(Long userId);
    List<RebalancingAlert> findByUserIdAndIsRead(Long userId, Boolean isRead);
    List<RebalancingAlert> findByPortfolioId(Long portfolioId);
}