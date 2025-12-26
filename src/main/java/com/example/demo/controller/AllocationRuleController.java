// src/main/java/com/example/demo/controller/AllocationRuleController.java
package com.example.demo.controller;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {

    private final AllocationRuleService service;

    public AllocationRuleController(AllocationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AssetClassAllocationRule> create(
            @RequestBody AssetClassAllocationRule rule) {
        return ResponseEntity.ok(service.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> update(
            @PathVariable Long id,
            @RequestBody AssetClassAllocationRule rule) {
        return ResponseEntity.ok(service.updateRule(id, rule));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<AssetClassAllocationRule>> byInvestor(
            @PathVariable Long investorId) {
        return ResponseEntity.ok(service.getRulesByInvestor(investorId));
    }

    @GetMapping("/investor/{investorId}/active")
    public ResponseEntity<List<AssetClassAllocationRule>> active(
            @PathVariable Long investorId) {
        return ResponseEntity.ok(service.getActiveRules(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> byId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRuleById(id));
    }
}
