package com.example.demo.Impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.entity.InvestorProfile;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.repository.InvestorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {
    
    @Autowired
    private AssetClassAllocationRuleRepository allocationRuleRepository;
    
    @Autowired
    private InvestorProfileRepository investorProfileRepository;
    
    @Override
    public AssetClassAllocationRule createAllocationRule(Long investorProfileId, AssetClassType assetClass, 
                                                         Double targetPercentage, Double tolerancePercentage) {
        InvestorProfile profile = investorProfileRepository.findById(investorProfileId).orElse(null);
        if (profile == null) {
            return null;
        }
        
        AssetClassAllocationRule rule = new AssetClassAllocationRule();
        rule.setAssetClass(assetClass);
        rule.setTargetPercentage(targetPercentage);
        rule.setTolerancePercentage(tolerancePercentage);
        rule.setInvestorProfile(profile);
        
        return allocationRuleRepository.save(rule);
    }
    
    @Override
    public List<AssetClassAllocationRule> getAllocationRulesByInvestorProfile(Long investorProfileId) {
        return allocationRuleRepository.findByInvestorProfileId(investorProfileId);
    }
    
    @Override
    public AssetClassAllocationRule getAllocationRuleById(Long id) {
        return allocationRuleRepository.findById(id).orElse(null);
    }
    
    @Override
    public AssetClassAllocationRule updateAllocationRule(Long id, Double targetPercentage, 
                                                        Double tolerancePercentage, Boolean isActive) {
        AssetClassAllocationRule rule = allocationRuleRepository.findById(id).orElse(null);
        if (rule != null) {
            rule.setTargetPercentage(targetPercentage);
            rule.setTolerancePercentage(tolerancePercentage);
            rule.setIsActive(isActive);
            return allocationRuleRepository.save(rule);
        }
        return null;
    }
    
    @Override
    public void deleteAllocationRule(Long id) {
        allocationRuleRepository.deleteById(id);
    }
    
    @Override
    public List<AssetClassAllocationRule> getActiveAllocationRules(Long investorProfileId) {
        return allocationRuleRepository.findByInvestorProfileIdAndIsActive(investorProfileId, true);
    }
}