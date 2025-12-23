package com.example.demo.controller;

import com.example.demo.Impl.RebalancingAlertService;
import com.example.demo.entity.RebalancingAlertRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rebalancing-alerts")
public class RebalancingAlertController {
    
    @Autowired
    private RebalancingAlertService alertService;
    
    @PostMapping("/check/{investorProfileId}")
    public ResponseEntity<List<RebalancingAlertRecord>> checkForAlerts(@PathVariable Long investorProfileId) {
        List<RebalancingAlertRecord> alerts = alertService.checkForRebalancingAlerts(investorProfileId);
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/investor-profile/{investorProfileId}")
    public ResponseEntity<List<RebalancingAlertRecord>> getAlertsByInvestorProfile(
            @PathVariable Long investorProfileId) {
        List<RebalancingAlertRecord> alerts = alertService.getAlertsByInvestorProfile(investorProfileId);
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/unresolved")
    public ResponseEntity<List<RebalancingAlertRecord>> getUnresolvedAlerts() {
        List<RebalancingAlertRecord> alerts = alertService.getUnresolvedAlerts();
        return ResponseEntity.ok(alerts);
    }
    
    @GetMapping("/severity/{severity}")
    public ResponseEntity<List<RebalancingAlertRecord>> getAlertsBySeverity(@PathVariable String severity) {
        List<RebalancingAlertRecord> alerts = alertService.getAlertsBySeverity(severity);
        return ResponseEntity.ok(alerts);
    }
    
    @PutMapping("/resolve/{alertId}")
    public ResponseEntity<RebalancingAlertRecord> resolveAlert(@PathVariable Long alertId) {
        RebalancingAlertRecord alert = alertService.resolveAlert(alertId);
        if (alert != null) {
            return ResponseEntity.ok(alert);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{alertId}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long alertId) {
        alertService.deleteAlert(alertId);
        return ResponseEntity.ok().build();
    }
}