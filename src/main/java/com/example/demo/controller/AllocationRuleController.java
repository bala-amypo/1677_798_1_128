package com.example.investor.api;

import com.example.investor.model.AssetClassAllocationRule;
import com.example.investor.service.AllocationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/allocation-rules")
public class AllocationRuleController {

    private final AllocationRuleService allocationRuleService;

    public AllocationRuleController(AllocationRuleService allocationRuleService) {
        this.allocationRuleService = allocationRuleService;
    }

    @PostMapping
    public ResponseEntity<AssetClassAllocationRule> createRule(@RequestBody AssetClassAllocationRule rule) {
        AssetClassAllocationRule created = allocationRuleService.createRule(rule);
        return ResponseEntity
                .created(URI.create("/api/allocation-rules/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> updateRule(
            @PathVariable Long id,
            @RequestBody AssetClassAllocationRule updatedRule) {
        AssetClassAllocationRule updated = allocationRuleService.updateRule(id, updatedRule);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<AssetClassAllocationRule>> getRulesByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(allocationRuleService.getRulesByInvestor(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> getRuleById(@PathVariable Long id) {
        AssetClassAllocationRule rule = allocationRuleService.getRuleById(id);
        return ResponseEntity.ok(rule);
    }

    @GetMapping
    public ResponseEntity<List<AssetClassAllocationRule>> getAllRules() {
        return ResponseEntity.ok(allocationRuleService.getAllRules());
    }
}
