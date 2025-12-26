package com.example.demo.service;

import com.example.demo.entity.InvestorProfile;

public interface InvestorProfileService {

    InvestorProfile createProfile(InvestorProfile profile);

    InvestorProfile getByInvestorId(String investorId);
}
