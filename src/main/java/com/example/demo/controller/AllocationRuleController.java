package com.example.demo.controller;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.impl.AllocationRuleServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {

    private final AllocationRuleServiceImpl service;

    // Spring finds the @Service and injects it here
    public AllocationRuleController(AllocationRuleServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public AssetClassAllocationRule create(@RequestBody AssetClassAllocationRule rule) {
        return service.createRule(rule);
    }

    @GetMapping("/{investorId}")
    public List<AssetClassAllocationRule> getByInvestor(@PathVariable Long investorId) {
        return service.getRulesByInvestor(investorId);
    }
}