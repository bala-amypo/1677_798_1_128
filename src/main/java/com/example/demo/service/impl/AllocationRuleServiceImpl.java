// src/main/java/com/example/demo/service/impl/AllocationRuleServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AssetClassAllocationRuleRepository repo;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        Double p = rule.getTargetPercentage();
        if (p == null || p < 0 || p > 100) {
            throw new IllegalArgumentException("between 0 and 100");
        }
        return repo.save(rule);
    }

    @Override
    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule updatedRule) {
        AssetClassAllocationRule existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found: " + id));
        Double p = updatedRule.getTargetPercentage();
        if (p == null || p < 0 || p > 100) {
            throw new IllegalArgumentException("between 0 and 100");
        }
        existing.setAssetClass(updatedRule.getAssetClass());
        existing.setTargetPercentage(updatedRule.getTargetPercentage());
        existing.setActive(updatedRule.getActive());
        existing.setInvestorId(updatedRule.getInvestorId());
        return repo.save(existing);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }

    @Override
    public List<AssetClassAllocationRule> getActiveRules(Long investorId) {
        return repo.findByInvestorIdAndActiveTrue(investorId);
    }

    @Override
    public AssetClassAllocationRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found: " + id));
    }
}
