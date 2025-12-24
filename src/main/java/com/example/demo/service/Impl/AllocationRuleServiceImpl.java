// package com.example.demo.service.impl;

// import com.example.demo.entity.AssetClassAllocationRule;
// import com.example.demo.repository.AssetClassAllocationRuleRepository;
// import com.example.demo.service.AllocationRuleService;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class AllocationRuleServiceImpl implements AllocationRuleService {

//     private final AssetClassAllocationRuleRepository repo;

//     public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repo) {
//         this.repo = repo;
//     }

//     public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
//         rule.validate();
//         return repo.save(rule);
//     }

//     public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule rule) {
//         AssetClassAllocationRule r = getRuleById(id);
//         r.setTargetPercentage(rule.getTargetPercentage());
//         r.setActive(rule.getActive());
//         r.validate();
//         return repo.save(r);
//     }

//     public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
//         return repo.findByInvestorId(investorId);
//     }

//     public List<AssetClassAllocationRule> getActiveRules(Long investorId) {
//         return repo.findActiveRulesHql(investorId);
//     }

//     public AssetClassAllocationRule getRuleById(Long id) {
//         return repo.findById(id)
//                 .orElseThrow(() -> new RuntimeException("not found"));
//     }

//     public List<AssetClassAllocationRule> getAllRules() {
//         return repo.findAll();
//     }
// }
