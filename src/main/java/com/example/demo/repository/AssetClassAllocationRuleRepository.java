// src/main/java/com/example/demo/repository/AssetClassAllocationRuleRepository.java
package com.example.demo.repository;

import com.example.demo.entity.AssetClassAllocationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetClassAllocationRuleRepository extends JpaRepository<AssetClassAllocationRule, Long> {

    List<AssetClassAllocationRule> findByInvestorId(Long investorId);

    List<AssetClassAllocationRule> findByInvestorIdAndActiveTrue(Long investorId);

    @Query("select r from AssetClassAllocationRule r where r.investorId = :investorId and r.active = true")
    List<AssetClassAllocationRule> findActiveRulesHql(Long investorId);
}
