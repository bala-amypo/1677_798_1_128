package com.example.demo.controller;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.AssetClassAllocationRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/allocation-rules")
@Tag(name = "Allocation Rules", description = "Asset class allocation rule management endpoints")
public class AssetClassAllocationRuleController {
    
    private final AssetClassAllocationRuleService allocationRuleService;
    
    public AssetClassAllocationRuleController(AssetClassAllocationRuleService allocationRuleService) {
        this.allocationRuleService = allocationRuleService;
    }
    
    @PostMapping("/")
    @Operation(summary = "Create a new allocation rule")
    public ResponseEntity<AssetClassAllocationRule> createRule(@RequestBody AssetClassAllocationRule rule) {
        AssetClassAllocationRule created = allocationRuleService.createRule(rule);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an allocation rule")
    public ResponseEntity<AssetClassAllocationRule> updateRule(
            @PathVariable Long id, 
            @RequestBody AssetClassAllocationRule rule) {
        AssetClassAllocationRule updated = allocationRuleService.updateRule(id, rule);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/investor/{investorId}")
    @Operation(summary = "Get allocation rules by investor")
    public ResponseEntity<List<AssetClassAllocationRule>> getRulesByInvestor(@PathVariable Long investorId) {
        List<AssetClassAllocationRule> rules = allocationRuleService.getRulesByInvestor(investorId);
        return ResponseEntity.ok(rules);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get allocation rule by ID")
    public ResponseEntity<AssetClassAllocationRule> getRuleById(@PathVariable Long id) {
        AssetClassAllocationRule rule = allocationRuleService.getRuleById(id);
        return ResponseEntity.ok(rule);
    }
    
    @GetMapping("/")
    @Operation(summary = "Get all allocation rules")
    public ResponseEntity<List<AssetClassAllocationRule>> getAllRules() {
        List<AssetClassAllocationRule> rules = allocationRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
}