package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfilesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/investors")
public class InvestorProfileController {

    private final InvestorProfilesService service;

    public InvestorProfileController(InvestorProfilesService service) {
        this.service = service;
    }

    @PostMapping
    public InvestorProfile create(@RequestBody InvestorProfile p) {
        return service.createInvestor(p);
    }

    @GetMapping("/{id}")
    public InvestorProfile get(@PathVariable Long id) {
        return service.getInvestorById(id);
    }

    @GetMapping
    public List<InvestorProfile> all() {
        return service.getAllInvestors();
    }

    @PutMapping("/{id}/status")
    public void status(@PathVariable Long id, @RequestParam boolean active) {
        service.updateInvestorStatus(id, active);
    }

    @GetMapping("/lookup/{investorId}")
    public InvestorProfile lookup(@PathVariable String investorId) {
        return service.findByInvestorId(investorId);
    }
}
