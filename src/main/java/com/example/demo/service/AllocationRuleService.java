package com.example.demo.Impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.entity.enums.AssetClassType;
import java.util.List;

public interface AllocationRuleService {
    AssetClassAllocationRule createAllocationRule(Long investorProfileId, AssetClassType assetClass, 
                                                  Double targetPercentage, Double tolerancePercentage);
    List<AssetClassAllocationRule> getAllocationRulesByInvestorProfile(Long investorProfileId);
    AssetClassAllocationRule getAllocationRuleById(Long id);
    AssetClassAllocationRule updateAllocationRule(Long id, Double targetPercentage, Double tolerancePercentage, Boolean isActive);
    void deleteAllocationRule(Long id);
    List<AssetClassAllocationRule> getActiveAllocationRules(Long investorProfileId);
}