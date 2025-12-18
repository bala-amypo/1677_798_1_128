package com.example.investor.api;

import com.example.investor.model.InvestorProfile;
import com.example.investor.service.InvestorProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/investors")
public class InvestorProfileController {

    private final InvestorProfileService investorProfileService;

    public InvestorProfileController(InvestorProfileService investorProfileService) {
        this.investorProfileService = investorProfileService;
    }

    @PostMapping
    public ResponseEntity<InvestorProfile> createInvestor(@RequestBody InvestorProfile investor) {
        InvestorProfile created = investorProfileService.createInvestor(investor);
        return ResponseEntity
                .created(URI.create("/api/investors/" + created.getId()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestorProfile> getInvestorById(@PathVariable Long id) {
        InvestorProfile investor = investorProfileService.getInvestorById(id);
        return ResponseEntity.ok(investor);
    }

    @GetMapping
    public ResponseEntity<List<InvestorProfile>> getAllInvestors() {
        return ResponseEntity.ok(investorProfileService.getAllInvestors());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<InvestorProfile> updateInvestorStatus(
            @PathVariable Long id,
            @RequestParam("active") boolean active) {
        InvestorProfile updated = investorProfileService.updateInvestorStatus(id, active);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/lookup/{investorId}")
    public ResponseEntity<InvestorProfile> findByInvestorId(@PathVariable String investorId) {
        InvestorProfile investor = investorProfileService.findByInvestorId(investorId);
        if (investor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(investor);
    }
}
