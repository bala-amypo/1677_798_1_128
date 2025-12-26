@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {
    private final AllocationSnapshotRecordRepository snapRepo;
    private final HoldingRecordRepository holdRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(AllocationSnapshotRecordRepository snapRepo, 
                                       HoldingRecordRepository holdRepo,
                                       AssetClassAllocationRuleRepository ruleRepo,
                                       RebalancingAlertRecordRepository alertRepo) {
        this.snapRepo = snapRepo;
        this.holdRepo = holdRepo;
        this.ruleRepo = ruleRepo;
        this.alertRepo = alertRepo;
    }

    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdRepo.findByInvestorId(investorId);
        if (holdings.isEmpty()) throw new IllegalArgumentException("No holdings found");

        double totalValue = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();
        
        // Calculate percentages and check rules
        List<AssetClassAllocationRule> rules = ruleRepo.findByInvestorIdAndActiveTrue(investorId);
        for (AssetClassAllocationRule rule : rules) {
            double currentAssetValue = holdings.stream()
                .filter(h -> h.getAssetClass() == rule.getAssetClass())
                .mapToDouble(HoldingRecord::getCurrentValue).sum();
            
            double currentPct = (currentAssetValue / totalValue) * 100;
            
            if (currentPct > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                    investorId, rule.getAssetClass(), currentPct, rule.getTargetPercentage(),
                    AlertSeverity.MEDIUM, "Rebalance needed", LocalDateTime.now(), false);
                alertRepo.save(alert);
            }
        }

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord(investorId, LocalDateTime.now(), totalValue, "{}");
        return snapRepo.save(snapshot);
    }

    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Snapshot not found: " + id));
    }
    public List<AllocationSnapshotRecord> getAllSnapshots() { return snapRepo.findAll(); }
}