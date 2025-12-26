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
        return repo.save(rule);
    }

    @Override
    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule ruleData) {
        AssetClassAllocationRule existing = getRuleById(id);
        existing.setTargetPercentage(ruleData.getTargetPercentage());
        existing.setActive(ruleData.getActive());
        return repo.save(existing);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }

    @Override
    public AssetClassAllocationRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found: " + id));
    }
}