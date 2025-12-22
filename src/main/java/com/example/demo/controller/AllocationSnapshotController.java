package com.example.demo.controller;

import com.example.demo.dto.AllocationSnapshotDto;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotService allocationSnapshotService;

    public AllocationSnapshotController(AllocationSnapshotService allocationSnapshotService) {
        this.allocationSnapshotService = allocationSnapshotService;
    }

    @PostMapping("/compute/{investorId}")
    public ResponseEntity<AllocationSnapshotDto> computeSnapshot(@PathVariable Long investorId) {
        return ResponseEntity.ok(allocationSnapshotService.computeSnapshot(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshotDto> getSnapshot(@PathVariable Long id) {
        return ResponseEntity.ok(allocationSnapshotService.getSnapshotById(id));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<AllocationSnapshotDto>> getSnapshotsByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(allocationSnapshotService.getSnapshotsByInvestor(investorId));
    }

    @GetMapping
    public ResponseEntity<List<AllocationSnapshotDto>> getAllSnapshots() {
        return ResponseEntity.ok(allocationSnapshotService.getAllSnapshots());
    }
}
