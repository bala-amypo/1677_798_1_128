package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AssetClassAllocationRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AssetClassAllocationRuleServiceImpl implements AssetClassAllocationRuleService {
    
    private final AssetClassAllocationRuleRepository repository;
    
    public AssetClassAllocationRuleServiceImpl(AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        if (rule.getTargetPercentage() < 0 || rule.getTargetPercentage() > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }
        return repository.save(rule);
    }
    
    @Override
    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule updatedRule) {
        AssetClassAllocationRule existingRule = getRuleById(id);
        
        if (updatedRule.getTargetPercentage() != null) {
            if (updatedRule.getTargetPercentage() < 0 || updatedRule.getTargetPercentage() > 100) {
                throw new IllegalArgumentException("Target percentage must be between 0 and 100");
            }
            existingRule.setTargetPercentage(updatedRule.getTargetPercentage());
        }
        
        if (updatedRule.getActive() != null) {
            existingRule.setActive(updatedRule.getActive());
        }
        
        if (updatedRule.getAssetClass() != null) {
            existingRule.setAssetClass(updatedRule.getAssetClass());
        }
        
        return repository.save(existingRule);
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
            .orElseThrow(() -> new IllegalArgumentException("Allocation rule not found"));
    }
    
    @Override
    public List<AssetClassAllocationRule> getAllRules() {
        return repository.findAll(); // ADDED THIS IMPLEMENTATION
    }
}