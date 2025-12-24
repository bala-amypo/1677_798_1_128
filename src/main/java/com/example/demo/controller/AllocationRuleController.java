// package com.example.demo.controller;

// import com.example.demo.entity.AssetClassAllocationRule;
// import com.example.demo.service.AllocationRuleService;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/allocation-rules")
// public class AllocationRuleController {

//     private final AllocationRuleService service;

//     public AllocationRuleController(AllocationRuleService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public AssetClassAllocationRule create(@RequestBody AssetClassAllocationRule r) {
//         return service.createRule(r);
//     }

//     @PutMapping("/{id}")
//     public AssetClassAllocationRule update(@PathVariable Long id,
//                                            @RequestBody AssetClassAllocationRule r) {
//         return service.updateRule(id, r);
//     }

//     @GetMapping("/investor/{investorId}")
//     public List<AssetClassAllocationRule> byInvestor(@PathVariable Long investorId) {
//         return service.getRulesByInvestor(investorId);
//     }

//     @GetMapping("/{id}")
//     public AssetClassAllocationRule get(@PathVariable Long id) {
//         return service.getRuleById(id);
//     }

//     @GetMapping
//     public List<AssetClassAllocationRule> all() {
//         return service.getAllRules();
//     }
// }
