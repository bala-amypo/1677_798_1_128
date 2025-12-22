package com.example.demo.controller;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {

    private final AllocationRuleService allocationRuleService;

    public AllocationRuleController(AllocationRuleService allocationRuleService) {
        this.allocationRuleService = allocationRuleService;
    }

    @PostMapping
    public ResponseEntity<AssetClassAllocationRule> createRule(
            @RequestParam Long investorId,
            @RequestParam String assetClass,
            @RequestParam Double targetPercentage) {
        AssetClassAllocationRule rule = allocationRuleService.createRule(investorId, assetClass, targetPercentage);
        if (rule != null) {
            return ResponseEntity.ok(rule);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> updateRule(
            @PathVariable Long id,
            @RequestParam(required = false) Double targetPercentage,
            @RequestParam(required = false) Boolean active) {
        AssetClassAllocationRule rule = allocationRuleService.updateRule(id, targetPercentage, active);
        if (rule != null) {
            return ResponseEntity.ok(rule);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> getRuleById(@PathVariable Long id) {
        AssetClassAllocationRule rule = allocationRuleService.getRuleById(id);
        if (rule != null) {
            return ResponseEntity.ok(rule);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<AssetClassAllocationRule>> getRulesByInvestor(@PathVariable Long investorId) {
        List<AssetClassAllocationRule> rules = allocationRuleService.getRulesByInvestor(investorId);
        return ResponseEntity.ok(rules);
    }

    @GetMapping
    public ResponseEntity<List<AssetClassAllocationRule>> getAllRules() {
        List<AssetClassAllocationRule> rules = allocationRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
}