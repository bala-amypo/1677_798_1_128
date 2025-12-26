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

    private final AssetClassAllocationRuleRepository repository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        validatePercentage(rule.getTargetPercentage());
        return repository.save(rule);
    }

    @Override
    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule rule) {
        AssetClassAllocationRule existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found: " + id));
        validatePercentage(rule.getTargetPercentage());
        existing.setAssetClass(rule.getAssetClass());
        existing.setTargetPercentage(rule.getTargetPercentage());
        existing.setActive(rule.getActive());
        existing.setInvestorId(rule.getInvestorId());
        return repository.save(existing);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    private void validatePercentage(Double value) {
        if (value == null || value < 0 || value > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }
    }
}
