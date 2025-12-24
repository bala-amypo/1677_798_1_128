package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AssetClassAllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetClassAllocationRuleServiceImpl implements AssetClassAllocationRuleService {

    private final AssetClassAllocationRuleRepository repository;

    public AssetClassAllocationRuleServiceImpl(AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        if (rule.getTargetPercentage() < 0 || rule.getTargetPercentage() > 100) {
            throw new RuntimeException("between 0 and 100");
        }
        return repository.save(rule);
    }

    @Override
    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule updatedRule) {
        AssetClassAllocationRule rule = getRuleById(id);
        rule.setAssetClass(updatedRule.getAssetClass());
        rule.setTargetPercentage(updatedRule.getTargetPercentage());
        rule.setActive(updatedRule.getActive());
        return repository.save(rule);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    @Override
    public List<AssetClassAllocationRule> getActiveRules(Long investorId) {
        return repository.findActiveRulesHql(investorId);
    }

    @Override
    public AssetClassAllocationRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
