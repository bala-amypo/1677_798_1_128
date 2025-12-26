package com.example.demo.controller;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.impl.RebalancingAlertServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class RebalancingAlertController {
    private final RebalancingAlertServiceImpl alertService;

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<RebalancingAlertRecord>> getAlerts(@PathVariable Long investorId) {
        return ResponseEntity.ok(alertService.getAlertsByInvestor(investorId));
    }

    @PostMapping("/{id}/resolve")
    public ResponseEntity<RebalancingAlertRecord> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }
}