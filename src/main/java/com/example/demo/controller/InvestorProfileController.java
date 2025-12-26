// src/main/java/com/example/demo/controller/InvestorProfileController.java
package com.example.demo.controller;

import com.example.demo.dto.InvestorProfileDTO;
import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/investors")
public class InvestorProfileController {

    private final InvestorProfileService service;

    public InvestorProfileController(InvestorProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InvestorProfileDTO> create(@RequestBody InvestorProfileDTO dto) {
        InvestorProfile entity = new InvestorProfile(
                dto.getInvestorId(), dto.getFullName(), dto.getEmail(),
                dto.getActive() == null || dto.getActive());
        InvestorProfile saved = service.createInvestor(entity);
        dto.setId(saved.getId());
        dto.setActive(saved.getActive());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestorProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getInvestorById(id));
    }

    @GetMapping
    public ResponseEntity<List<InvestorProfile>> all() {
        return ResponseEntity.ok(service.getAllInvestors());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<InvestorProfile> updateStatus(@PathVariable Long id,
                                                        @RequestParam boolean active) {
        return ResponseEntity.ok(service.updateInvestorStatus(id, active));
    }

    @GetMapping("/lookup/{investorId}")
    public ResponseEntity<InvestorProfile> byInvestorId(@PathVariable String investorId) {
        return service.findByInvestorId(investorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
