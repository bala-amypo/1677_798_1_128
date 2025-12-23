package com.example.demo.Impl;

import com.example.demo.entity.InvestorProfile;
import java.util.List;

public interface InvestorProfilesService {
    InvestorProfile createInvestorProfile(String name, String investorId, Long userId);
    InvestorProfile getInvestorProfileById(Long id);
    InvestorProfile getInvestorProfileByInvestorId(String investorId);
    List<InvestorProfile> getAllInvestorProfiles();
    InvestorProfile updateInvestorProfile(Long id, String name);
    void deleteInvestorProfile(Long id);
}