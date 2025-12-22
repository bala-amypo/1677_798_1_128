package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AssetClassAllocationRuleRepository ruleRepository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    @Transactional
    public AssetClassAllocationRule createRule(Long investorId, String assetClass, Double targetPercentage) {
        // Validate target percentage
        if (targetPercentage <= 0 || targetPercentage > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }

        // Check if rule already exists for this investor and asset class
        AssetClassAllocationRule existingRule = ruleRepository
                .findByInvestorIdAndAssetClass(investorId, AssetClassType.valueOf(assetClass));
        
        if (existingRule != null) {
            throw new IllegalStateException("Rule already exists for this asset class");
        }

        // Calculate total allocation
        Double totalAllocation = calculateTotalAllocation(investorId);
        if (totalAllocation + targetPercentage > 100) {
            throw new IllegalStateException("Total allocation would exceed 100%");
        }

        // Create new rule
        AssetClassAllocationRule rule = new AssetClassAllocationRule();
        rule.setInvestorId(investorId);
        rule.setAssetClass(AssetClassType.valueOf(assetClass));
        rule.setTargetPercentage(targetPercentage);
        rule.setActive(true);

        return ruleRepository.save(rule);
    }

    @Override
    @Transactional
    public AssetClassAllocationRule updateRule(Long ruleId, Double targetPercentage, Boolean active) {
        AssetClassAllocationRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new IllegalArgumentException("Rule not found with id: " + ruleId));

        if (targetPercentage != null) {
            if (targetPercentage <= 0 || targetPercentage > 100) {
                throw new IllegalArgumentException("Target percentage must be between 0 and 100");
            }

            // Recalculate total allocation excluding current rule
            Double currentTotal = calculateTotalAllocation(rule.getInvestorId());
            Double currentRulePercentage = rule.getTargetPercentage();
            Double newTotal = currentTotal - currentRulePercentage + targetPercentage;
            
            if (newTotal > 100) {
                throw new IllegalStateException("Total allocation would exceed 100%");
            }
            
            rule.setTargetPercentage(targetPercentage);
        }

        if (active != null) {
            rule.setActive(active);
        }

        return ruleRepository.save(rule);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return ruleRepository.findByInvestorIdOrderByAssetClass(investorId);
    }

    @Override
    @Transactional
    public void deleteRule(Long ruleId) {
        if (!ruleRepository.existsById(ruleId)) {
            throw new IllegalArgumentException("Rule not found with id: " + ruleId);
        }
        ruleRepository.deleteById(ruleId);
    }

    @Override
    public Double calculateTotalAllocation(Long investorId) {
        List<AssetClassAllocationRule> rules = ruleRepository.findByInvestorIdAndActiveTrue(investorId);
        return rules.stream()
                .mapToDouble(AssetClassAllocationRule::getTargetPercentage)
                .sum();
    }

    @Override
    @Transactional
    public AssetClassAllocationRule toggleRuleStatus(Long ruleId) {
        AssetClassAllocationRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new IllegalArgumentException("Rule not found with id: " + ruleId));
        
        rule.setActive(!rule.isActive());
        return ruleRepository.save(rule);
    }
}