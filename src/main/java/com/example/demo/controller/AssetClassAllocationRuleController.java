package com.example.demo.controller;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.AssetClassAllocationRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocation-rules")
@Tag(name = "Allocation Rules")
public class AssetClassAllocationRuleController {

    private final AssetClassAllocationRuleService service;

    public AssetClassAllocationRuleController(AssetClassAllocationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public AssetClassAllocationRule create(@RequestBody AssetClassAllocationRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public AssetClassAllocationRule update(@PathVariable Long id,
                                           @RequestBody AssetClassAllocationRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping("/investor/{investorId}")
    public List<AssetClassAllocationRule> byInvestor(@PathVariable Long investorId) {
        return service.getRulesByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public AssetClassAllocationRule get(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping
    public List<AssetClassAllocationRule> all() {
        return service.getAllRules();
    }
}
