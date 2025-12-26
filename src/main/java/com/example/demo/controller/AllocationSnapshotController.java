package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.impl.AllocationSnapshotServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotServiceImpl snapshotService;

    public AllocationSnapshotController(AllocationSnapshotServiceImpl snapshotService) {
        this.snapshotService = snapshotService;
    }

    @PostMapping("/compute/{investorId}")
    public ResponseEntity<AllocationSnapshotRecord> computeSnapshot(@PathVariable Long investorId) {
        return ResponseEntity.ok(snapshotService.computeSnapshot(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshotRecord> getSnapshot(@PathVariable Long id) {
        return ResponseEntity.ok(snapshotService.getSnapshotById(id));
    }

    @GetMapping
    public ResponseEntity<List<AllocationSnapshotRecord>> getAllSnapshots() {
        return ResponseEntity.ok(snapshotService.getAllSnapshots());
    }
}