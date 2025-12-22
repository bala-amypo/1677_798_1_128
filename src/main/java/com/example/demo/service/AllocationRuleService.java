package com.example.demo.service;

import com.example.demo.entity.AssetClassAllocationRule;
import java.util.List;

public interface AllocationRuleService {
    AssetClassAllocationRule createRule(Long investorId, String assetClass, Double targetPercentage);
    AssetClassAllocationRule updateRule(Long ruleId, Double targetPercentage, Boolean active);
    List<AssetClassAllocationRule> getRulesByInvestor(Long investorId);
    AssetClassAllocationRule getRuleById(Long id);
    List<AssetClassAllocationRule> getAllRules();
}