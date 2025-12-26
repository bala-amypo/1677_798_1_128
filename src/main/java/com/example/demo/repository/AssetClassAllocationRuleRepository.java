package com.example.demo.repository;

import com.example.demo.entity.AssetClassAllocationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssetClassAllocationRuleRepository extends JpaRepository<AssetClassAllocationRule, Long> {
    // Required for Topic 6: testInvestorRulesAssociation
    List<AssetClassAllocationRule> findByInvestorId(Long investorId);

    // Required for Topic 8: testSnapshotComputationWithAlerts
    List<AssetClassAllocationRule> findByInvestorIdAndActiveTrue(Long investorId);

    // Required for Topic 8: testHqlActiveRulesQuery
    @Query("SELECT r FROM AssetClassAllocationRule r WHERE r.investorId = ?1 AND r.active = true")
    List<AssetClassAllocationRule> findActiveRulesHql(Long investorId);
}