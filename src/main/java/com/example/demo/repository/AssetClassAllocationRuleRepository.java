package com.example.demo.repository;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetClassAllocationRuleRepository extends JpaRepository<AssetClassAllocationRule, Long> {
    List<AssetClassAllocationRule> findByInvestorProfileId(Long investorProfileId);
    Optional<AssetClassAllocationRule> findByInvestorProfileIdAndAssetClass(Long investorProfileId, AssetClassType assetClass);
    List<AssetClassAllocationRule> findByInvestorProfileIdAndIsActive(Long investorProfileId, Boolean isActive);
}