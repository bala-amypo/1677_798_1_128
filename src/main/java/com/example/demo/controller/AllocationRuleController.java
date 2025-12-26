package com.example.demo.controller;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.impl.AllocationRuleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {

    private final AllocationRuleServiceImpl ruleService;

    public AllocationRuleController(AllocationRuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<AssetClassAllocationRule> createRule(@RequestBody AssetClassAllocationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> updateRule(@PathVariable Long id, @RequestBody AssetClassAllocationRule rule) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<AssetClassAllocationRule>> getRulesByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(ruleService.getRulesByInvestor(investorId));
    }
}