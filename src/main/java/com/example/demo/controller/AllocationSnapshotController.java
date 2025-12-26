// src/main/java/com/example/demo/controller/AllocationSnapshotController.java
package com.example.demo.controller;

import com.example.demo.dto.SnapshotDTO;
import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotService service;

    public AllocationSnapshotController(AllocationSnapshotService service) {
        this.service = service;
    }

    @PostMapping("/compute/{investorId}")
    public ResponseEntity<SnapshotDTO> compute(@PathVariable Long investorId) {
        AllocationSnapshotRecord snap = service.computeSnapshot(investorId);
        SnapshotDTO dto = toDto(snap);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SnapshotDTO> get(@PathVariable Long id) {
        AllocationSnapshotRecord snap = service.getSnapshotById(id);
        return ResponseEntity.ok(toDto(snap));
    }

    @GetMapping
    public ResponseEntity<List<SnapshotDTO>> all() {
        List<SnapshotDTO> list = service.getAllSnapshots().stream()
                .map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    private SnapshotDTO toDto(AllocationSnapshotRecord snap) {
        SnapshotDTO dto = new SnapshotDTO();
        dto.setId(snap.getId());
        dto.setInvestorId(snap.getInvestorId());
        dto.setSnapshotDate(snap.getSnapshotDate());
        dto.setTotalPortfolioValue(snap.getTotalPortfolioValue());
        dto.setAllocationJson(snap.getAllocationJson());
        return dto;
    }
}
