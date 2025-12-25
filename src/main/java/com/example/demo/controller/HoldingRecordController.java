package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/holdings")
@Tag(name = "Holdings")
public class HoldingRecordController {
    private final HoldingRecordService service;

    public HoldingRecordController(HoldingRecordService service) {
        this.service = service;
    }

    @PostMapping("/")
    public HoldingRecord create(@RequestBody HoldingRecord record) {
        return service.recordHolding(record);
    }

    @GetMapping("/investor/{investorId}")
    public List<HoldingRecord> getByInvestor(@PathVariable Long investorId) {
        return service.getHoldingsByInvestor(investorId);
    }

    @GetMapping("/")
    public List<HoldingRecord> list() {
        return service.getAllHoldings();
    }
}