@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {
    private final AssetClassAllocationRuleRepository repo;
    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repo) { this.repo = repo; }

    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        if (rule.getTargetPercentage() < 0 || rule.getTargetPercentage() > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        return repo.save(rule);
    }

    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule data) {
        AssetClassAllocationRule existing = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rule not found: " + id));
        existing.setTargetPercentage(data.getTargetPercentage());
        return repo.save(existing);
    }
    
    public List<AssetClassAllocationRule> getRulesByInvestor(Long id) { return repo.findByInvestorId(id); }
}