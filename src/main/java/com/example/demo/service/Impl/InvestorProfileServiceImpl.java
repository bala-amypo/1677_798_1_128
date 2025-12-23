package com.example.demo.Impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorProfilesServiceImpl implements InvestorProfilesService {
    
    @Autowired
    private InvestorProfileRepository investorProfileRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public InvestorProfile createInvestorProfile(String name, String investorId, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        
        InvestorProfile profile = new InvestorProfile();
        profile.setName(name);
        profile.setInvestorId(investorId);
        profile.setUser(user);
        
        return investorProfileRepository.save(profile);
    }
    
    @Override
    public InvestorProfile getInvestorProfileById(Long id) {
        return investorProfileRepository.findById(id).orElse(null);
    }
    
    @Override
    public InvestorProfile getInvestorProfileByInvestorId(String investorId) {
        return investorProfileRepository.findByInvestorId(investorId).orElse(null);
    }
    
    @Override
    public List<InvestorProfile> getAllInvestorProfiles() {
        return investorProfileRepository.findAll();
    }
    
    @Override
    public InvestorProfile updateInvestorProfile(Long id, String name) {
        InvestorProfile profile = investorProfileRepository.findById(id).orElse(null);
        if (profile != null) {
            profile.setName(name);
            return investorProfileRepository.save(profile);
        }
        return null;
    }
    
    @Override
    public void deleteInvestorProfile(Long id) {
        investorProfileRepository.deleteById(id);
    }
}