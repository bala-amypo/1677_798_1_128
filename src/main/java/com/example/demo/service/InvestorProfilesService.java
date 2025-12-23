package com.example.demo.service;

import com.example.demo.entity.InvestorProfile;
import java.util.Optional;

public interface InvestorProfilesService {

    Optional<InvestorProfile> getInvestorProfileByInvestorId(String investorId);

    InvestorProfile saveInvestorProfile(InvestorProfile profile);
}
