package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AssetClassAllocationRuleRepository ruleRepository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public AssetClassAllocationRule createRule(Long investorId, String assetClass, Double targetPercentage) {
        AssetClassAllocationRule rule = new AssetClassAllocationRule();
        rule.setInvestorId(investorId);
        rule.setAssetClass(assetClass);
        rule.setTargetPercentage(targetPercentage);
        rule.setActive(true);
        
        return ruleRepository.save(rule);
    }

    @Override
    public AssetClassAllocationRule updateRule(Long ruleId, Double targetPercentage, Boolean active) {
        AssetClassAllocationRule rule = ruleRepository.findById(ruleId).orElse(null);
        if (rule == null) {
            return null;
        }

        if (targetPercentage != null) {
            rule.setTargetPercentage(targetPercentage);
        }

        if (active != null) {
            rule.setActive(active);
        }

        return ruleRepository.save(rule);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return ruleRepository.findByInvestorId(investorId);
    }

    @Override
    public AssetClassAllocationRule getRuleById(Long id) {
        return ruleRepository.findById(id).orElse(null);
    }

    @Override
    public List<AssetClassAllocationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}