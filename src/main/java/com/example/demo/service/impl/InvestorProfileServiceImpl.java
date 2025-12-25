package com.example.demo.service.impl;
import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvestorProfileServiceImpl implements InvestorProfileService {
    private final InvestorProfileRepository repo;
    public InvestorProfileServiceImpl(InvestorProfileRepository repo) { this.repo = repo; }

    @Override
    public InvestorProfile createInvestor(InvestorProfile inv) { return repo.save(inv); }
    @Override
    public InvestorProfile getInvestorById(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("not found")); }
    @Override
    public Optional<com.example.demo.entity.InvestorProfile> findByInvestorId(String iId) { return repo.findByInvestorId(iId); }
    @Override
    public List<InvestorProfile> getAllInvestors() { return repo.findAll(); }
    @Override
    public InvestorProfile updateInvestorStatus(Long id, boolean active) {
        InvestorProfile inv = getInvestorById(id);
        inv.setActive(active);
        return repo.save(inv);
    }
}