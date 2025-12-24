package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AssetClassAllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetClassAllocationRuleServiceImpl
        implements AssetClassAllocationRuleService {

    private final AssetClassAllocationRuleRepository repository;

    public AssetClassAllocationRuleServiceImpl(
            AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        return repository.save(rule);
    }

    @Override
    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule rule) {
        AssetClassAllocationRule existing = getRuleById(id);
        existing.setAssetClass(rule.getAssetClass());
        existing.setTargetPercentage(rule.getTargetPercentage());
        existing.setActive(rule.getActive());
        return repository.save(existing);
    }

    @Override
    public AssetClassAllocationRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
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
    public List<AssetClassAllocationRule> getAllRules() {
        return repository.findAll();
    }
}
