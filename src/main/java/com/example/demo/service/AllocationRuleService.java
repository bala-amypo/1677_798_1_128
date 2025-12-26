package com.example.demo.service;

import com.example.demo.entity.AssetClassAllocationRule;

import java.util.List;

public interface AllocationRuleService {

    AssetClassAllocationRule save(AssetClassAllocationRule rule);

    List<AssetClassAllocationRule> findActiveRules(Long investorId);
}
