package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/holdings")
public class HoldingRecordController {

    private final HoldingRecordService service;

    public HoldingRecordController(HoldingRecordService service) {
        this.service = service;
    }

    @PostMapping
    public HoldingRecord create(@RequestBody HoldingRecord h) {
        return service.recordHolding(h);
    }

    @GetMapping("/investor/{id}")
    public List<HoldingRecord> byInvestor(@PathVariable Long id) {
        return service.getHoldingsByInvestor(id);
    }

    @GetMapping("/{id}")
    public HoldingRecord get(@PathVariable Long id) {
        return service.getHoldingById(id);
    }

    @GetMapping
    public List<HoldingRecord> all() {
        return service.getAllHoldings();
    }
}
