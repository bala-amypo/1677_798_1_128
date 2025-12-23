package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfilesService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvestorProfilesServiceImpl implements InvestorProfilesService {

    private final InvestorProfileRepository repo;

    public InvestorProfilesServiceImpl(InvestorProfileRepository repo) {
        this.repo = repo;
    }

    public InvestorProfile createInvestor(InvestorProfile investor) {
        return repo.save(investor);
    }

    public InvestorProfile getInvestorById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public InvestorProfile findByInvestorId(String investorId) {
        return repo.findByInvestorId(investorId)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<InvestorProfile> getAllInvestors() {
        return repo.findAll();
    }

    public void updateInvestorStatus(Long id, boolean active) {
        InvestorProfile p = getInvestorById(id);
        p.setActive(active);
        repo.save(p);
    }
}
