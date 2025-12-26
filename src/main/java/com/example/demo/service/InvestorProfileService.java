package com.example.demo.service;

import com.example.demo.entity.InvestorProfile;

import java.util.List;

public interface InvestorProfileService {

    InvestorProfile createInvestor(InvestorProfile profile);

    InvestorProfile getInvestorById(Long id);

    List<InvestorProfile> getAllInvestors();

    InvestorProfile updateInvestorStatus(Long id, boolean active);
}
