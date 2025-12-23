package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investor-profiles")
public class InvestorProfileController {
    
    @Autowired
    private InvestorProfileService investorProfileService;
    
    @PostMapping
    public ResponseEntity<InvestorProfile> createProfile(@RequestBody InvestorProfile profile) {
        InvestorProfile createdProfile = investorProfileService.createProfile(profile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InvestorProfile> updateProfile(@PathVariable Long id, @RequestBody InvestorProfile profile) {
        InvestorProfile updatedProfile = investorProfileService.updateProfile(id, profile);
        return ResponseEntity.ok(updatedProfile);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        investorProfileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InvestorProfile> getProfileById(@PathVariable Long id) {
        Optional<InvestorProfile> profile = investorProfileService.getProfileById(id);
        return profile.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<InvestorProfile> getProfileByUserId(@PathVariable Long userId) {
        Optional<InvestorProfile> profile = investorProfileService.getProfileByUserId(userId);
        return profile.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<InvestorProfile>> getAllProfiles() {
        List<InvestorProfile> profiles = investorProfileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }
    
    @GetMapping("/risk-tolerance/{riskTolerance}")
    public ResponseEntity<List<InvestorProfile>> getProfilesByRiskTolerance(@PathVariable String riskTolerance) {
        List<InvestorProfile> profiles = investorProfileService.getProfilesByRiskTolerance(riskTolerance);
        return ResponseEntity.ok(profiles);
    }
    
    @PatchMapping("/user/{userId}/portfolio-value")
    public ResponseEntity<Void> updatePortfolioValue(@PathVariable Long userId, @RequestParam BigDecimal currentValue) {
        investorProfileService.updatePortfolioValue(userId, currentValue);
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/user/{userId}/investment-objective")
    public ResponseEntity<Void> updateInvestmentObjective(@PathVariable Long userId, @RequestParam String objective) {
        investorProfileService.updateInvestmentObjective(userId, objective);
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/user/{userId}/status")
    public ResponseEntity<Void> toggleProfileStatus(@PathVariable Long userId, @RequestParam Boolean isActive) {
        investorProfileService.toggleProfileStatus(userId, isActive);
        return ResponseEntity.ok().build();
    }
}