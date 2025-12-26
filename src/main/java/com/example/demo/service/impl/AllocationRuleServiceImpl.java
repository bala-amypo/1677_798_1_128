package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllocationRuleServiceImpl { // Renamed to match your test
    private final AssetClassAllocationRuleRepository repository;

    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        if (rule.getTargetPercentage() < 0 || rule.getTargetPercentage() > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }
        return repository.save(rule);
    }

    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule details) {
        AssetClassAllocationRule rule = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
        rule.setTargetPercentage(details.getTargetPercentage());
        return repository.save(rule);
    }

    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }
}