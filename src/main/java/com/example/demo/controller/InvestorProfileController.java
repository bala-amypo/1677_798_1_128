package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfilesService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/investors")
public class InvestorProfileController {

    private final InvestorProfilesService investorProfilesService;

    public InvestorProfileController(InvestorProfilesService investorProfilesService) {
        this.investorProfilesService = investorProfilesService;
    }

    @GetMapping("/{investorId}")
    public Optional<InvestorProfile> getInvestor(@PathVariable String investorId) {
        return investorProfilesService.getInvestorProfileByInvestorId(investorId);
    }

    @PostMapping
    public InvestorProfile createInvestor(@RequestBody InvestorProfile profile) {
        return investorProfilesService.saveInvestorProfile(profile);
    }
}
