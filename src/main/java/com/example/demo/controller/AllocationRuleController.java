package com.example.demo.controller;

import com.example.demo.Impl.AllocationRuleService;
import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocation-rules")
public class AllocationRuleController {
    
    @Autowired
    private AllocationRuleService allocationRuleService;
    
    @PostMapping
    public ResponseEntity<AssetClassAllocationRule> createAllocationRule(
            @RequestParam Long investorProfileId,
            @RequestParam AssetClassType assetClass,
            @RequestParam Double targetPercentage,
            @RequestParam Double tolerancePercentage) {
        AssetClassAllocationRule rule = allocationRuleService.createAllocationRule(
                investorProfileId, assetClass, targetPercentage, tolerancePercentage);
        if (rule != null) {
            return ResponseEntity.ok(rule);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @GetMapping("/investor-profile/{investorProfileId}")
    public ResponseEntity<List<AssetClassAllocationRule>> getAllocationRulesByInvestorProfile(
            @PathVariable Long investorProfileId) {
        List<AssetClassAllocationRule> rules = allocationRuleService.getAllocationRulesByInvestorProfile(investorProfileId);
        return ResponseEntity.ok(rules);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> getAllocationRuleById(@PathVariable Long id) {
        AssetClassAllocationRule rule = allocationRuleService.getAllocationRuleById(id);
        if (rule != null) {
            return ResponseEntity.ok(rule);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> updateAllocationRule(
            @PathVariable Long id,
            @RequestParam Double targetPercentage,
            @RequestParam Double tolerancePercentage,
            @RequestParam Boolean isActive) {
        AssetClassAllocationRule rule = allocationRuleService.updateAllocationRule(
                id, targetPercentage, tolerancePercentage, isActive);
        if (rule != null) {
            return ResponseEntity.ok(rule);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAllocationRule(@PathVariable Long id) {
        allocationRuleService.deleteAllocationRule(id);
        return ResponseEntity.ok().build();
    }
}