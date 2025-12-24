package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/investors")
@Tag(name = "Investor Profiles", description = "Investor profile management endpoints")
public class InvestorProfileController {
    
    private final InvestorProfileService investorProfileService;
    
    public InvestorProfileController(InvestorProfileService investorProfileService) {
        this.investorProfileService = investorProfileService;
    }
    
    @PostMapping("/")
    @Operation(summary = "Create a new investor profile")
    public ResponseEntity<InvestorProfile> createInvestor(@RequestBody InvestorProfile investor) {
        InvestorProfile created = investorProfileService.createInvestor(investor);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get investor by ID")
    public ResponseEntity<InvestorProfile> getInvestorById(@PathVariable Long id) {
        InvestorProfile investor = investorProfileService.getInvestorById(id);
        return ResponseEntity.ok(investor);
    }
    
    @GetMapping("/")
    @Operation(summary = "Get all investors")
    public ResponseEntity<List<InvestorProfile>> getAllInvestors() {
        List<InvestorProfile> investors = investorProfileService.getAllInvestors();
        return ResponseEntity.ok(investors);
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update investor status")
    public ResponseEntity<InvestorProfile> updateInvestorStatus(
            @PathVariable Long id, 
            @RequestBody Map<String, Boolean> request) {
        boolean active = request.get("active");
        InvestorProfile updated = investorProfileService.updateInvestorStatus(id, active);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/lookup/{investorId}")
    @Operation(summary = "Lookup investor by investor ID")
    public ResponseEntity<InvestorProfile> lookupByInvestorId(@PathVariable String investorId) {
        InvestorProfile investor = investorProfileService.findByInvestorId(investorId);
        return ResponseEntity.ok(investor);
    }
}