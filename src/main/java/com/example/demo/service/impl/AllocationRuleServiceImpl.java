package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllocationRuleServiceImpl {

    private final AssetClassAllocationRuleRepository ruleRepository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        return ruleRepository.save(rule);
    }

    public AssetClassAllocationRule updateRule(long id, AssetClassAllocationRule updatedRule) {
        Optional<AssetClassAllocationRule> existing = ruleRepository.findById(id);
        if (existing.isPresent()) {
            AssetClassAllocationRule rule = existing.get();
            rule.setAssetClass(updatedRule.getAssetClass());
            rule.setTargetPercentage(updatedRule.getTargetPercentage());
            return ruleRepository.save(rule);
        }
        return null;
    }

    public List<AssetClassAllocationRule> getRulesByInvestor(long investorId) {
        return ruleRepository.findByInvestorId(investorId);
    }
}
