public interface InvestorProfileService {

    InvestorProfile createInvestor(InvestorProfile investor);

    InvestorProfile getInvestorById(Long id); 

    InvestorProfile findByInvestorId(String investorId);

    List<InvestorProfile> getAllInvestors();

    InvestorProfile updateInvestorStatus(Long id, boolean active);
}
