@Service
public class HoldingRecordServiceImpl implements HoldingRecordService {
    private final HoldingRecordRepository repo;
    public HoldingRecordServiceImpl(HoldingRecordRepository repo) { this.repo = repo; }

    public HoldingRecord recordHolding(HoldingRecord record) {
        if (record.getCurrentValue() <= 0) throw new IllegalArgumentException("Value must be > 0");
        return repo.save(record);
    }
    public List<HoldingRecord> getHoldingsByInvestor(Long id) { return repo.findByInvestorId(id); }
    public Optional<HoldingRecord> getHoldingById(Long id) { return repo.findById(id); }
}