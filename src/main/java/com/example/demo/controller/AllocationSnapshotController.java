package com.example.demo.controller;

import com.example.demo.Impl.AllocationSnapshotService;
import com.example.demo.entity.AllocationSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/allocation-snapshots")
public class AllocationSnapshotController {
    
    @Autowired
    private AllocationSnapshotService snapshotService;
    
    @PostMapping("/{investorProfileId}")
    public ResponseEntity<AllocationSnapshot> createSnapshot(@PathVariable Long investorProfileId) {
        AllocationSnapshot snapshot = snapshotService.createSnapshot(investorProfileId);
        if (snapshot != null) {
            return ResponseEntity.ok(snapshot);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshot> getSnapshotById(@PathVariable Long id) {
        AllocationSnapshot snapshot = snapshotService.getSnapshotById(id);
        if (snapshot != null) {
            return ResponseEntity.ok(snapshot);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/investor-profile/{investorProfileId}")
    public ResponseEntity<List<AllocationSnapshot>> getSnapshotsByInvestorProfile(
            @PathVariable Long investorProfileId) {
        List<AllocationSnapshot> snapshots = snapshotService.getSnapshotsByInvestorProfile(investorProfileId);
        return ResponseEntity.ok(snapshots);
    }
    
    @GetMapping("/time-range")
    public ResponseEntity<List<AllocationSnapshot>> getSnapshotsByTimeRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<AllocationSnapshot> snapshots = snapshotService.getSnapshotsByTimeRange(start, end);
        return ResponseEntity.ok(snapshots);
    }
    
    @GetMapping("/allocations/{investorProfileId}")
    public ResponseEntity<Map<String, Double>> calculateAssetAllocations(@PathVariable Long investorProfileId) {
        Map<String, Double> allocations = snapshotService.calculateAssetAllocations(investorProfileId);
        return ResponseEntity.ok(allocations);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSnapshot(@PathVariable Long id) {
        snapshotService.deleteSnapshot(id);
        return ResponseEntity.ok().build();
    }
}