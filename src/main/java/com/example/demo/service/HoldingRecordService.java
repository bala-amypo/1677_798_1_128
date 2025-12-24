package com.example.demo.service;

import com.example.demo.entity.HoldingRecord;
import java.util.List;

public interface HoldingRecordService {

    HoldingRecord recordHolding(HoldingRecord holding);

    HoldingRecord getHoldingById(Long id);

    List<HoldingRecord> getHoldingsByInvestor(Long investorId);

    List<HoldingRecord> getAllHoldings();
}
