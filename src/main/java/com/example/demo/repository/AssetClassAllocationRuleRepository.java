package com.example.demo.repository;

import com.example.demo.entity.AssetClassAllocationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetClassAllocationRuleRepository extends JpaRepository<AssetClassAllocationRule, Long> {
    List<AssetClassAllocationRule> findByInvestorId(Long investorId);
    AssetClassAllocationRule findByInvestorIdAndAssetClass(Long investorId, String assetClass);
}