package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.impl.InvestorProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investors")
@RequiredArgsConstructor
public class InvestorProfileController {
    private final InvestorProfileServiceImpl investorProfileService;

    @PostMapping
    public ResponseEntity<InvestorProfile> create(@RequestBody InvestorProfile investor) {
        return ResponseEntity.ok(investorProfileService.createInvestor(investor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestorProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(investorProfileService.getInvestorById(id));
    }

    @GetMapping
    public ResponseEntity<List<InvestorProfile>> getAll() {
        return ResponseEntity.ok(investorProfileService.getAllInvestors());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<InvestorProfile> updateStatus(@PathVariable Long id, @RequestParam Boolean active) {
        return ResponseEntity.ok(investorProfileService.updateInvestorStatus(id, active));
    }
}