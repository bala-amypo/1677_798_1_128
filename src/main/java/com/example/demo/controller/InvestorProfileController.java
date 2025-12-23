package com.example.demo.controller;

import com.example.demo.Impl.InvestorProfilesService;
import com.example.demo.entity.InvestorProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investor-profiles")
public class InvestorProfileController {
    
    @Autowired
    private InvestorProfilesService investorProfileService;
    
    @PostMapping
    public ResponseEntity<InvestorProfile> createInvestorProfile(@RequestParam String name,
                                                                @RequestParam String investorId,
                                                                @RequestParam Long userId) {
        InvestorProfile profile = investorProfileService.createInvestorProfile(name, investorId, userId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InvestorProfile> getInvestorProfileById(@PathVariable Long id) {
        InvestorProfile profile = investorProfileService.getInvestorProfileById(id);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<InvestorProfile>> getAllInvestorProfiles() {
        List<InvestorProfile> profiles = investorProfileService.getAllInvestorProfiles();
        return ResponseEntity.ok(profiles);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InvestorProfile> updateInvestorProfile(@PathVariable Long id,
                                                                 @RequestParam String name) {
        InvestorProfile profile = investorProfileService.updateInvestorProfile(id, name);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestorProfile(@PathVariable Long id) {
        investorProfileService.deleteInvestorProfile(id);
        return ResponseEntity.ok().build();
    }
}