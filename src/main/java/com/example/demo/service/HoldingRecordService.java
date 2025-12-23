package com.example.demo.service;

import com.example.demo.entity.HoldingRecord;

import java.util.List;

public interface HoldingRecordService {

    HoldingRecord save(HoldingRecord holding);

    List<HoldingRecord> getByInvestor(Long investorId);

    List<HoldingRecord> getHighValueHoldings(Double minValue);
}
