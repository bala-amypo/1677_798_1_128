package com.example.demo.controller;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.RebalancingAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class RebalancingAlertController {

    private final RebalancingAlertService alertService;

    public RebalancingAlertController(RebalancingAlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<RebalancingAlertRecord> create(
            @RequestBody RebalancingAlertRecord alert
    ) {
        return ResponseEntity.ok(alertService.createAlert(alert));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<RebalancingAlertRecord> resolve(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<RebalancingAlertRecord>> getByInvestor(
            @PathVariable Long investorId
    ) {
        return ResponseEntity.ok(
                alertService.getAlertsByInvestor(investorId)
        );
    }
}
