package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.impl.AllocationSnapshotServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/snapshots")
@RequiredArgsConstructor
public class AllocationSnapshotController {
    private final AllocationSnapshotServiceImpl snapshotService;

    @PostMapping("/compute/{investorId}")
    public ResponseEntity<AllocationSnapshotRecord> compute(@PathVariable Long investorId) {
        return ResponseEntity.ok(snapshotService.computeSnapshot(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshotRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(snapshotService.getSnapshotById(id));
    }
}