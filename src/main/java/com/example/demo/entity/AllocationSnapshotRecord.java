import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import java.time.LocalDateTime;

@Entity
@Table(name = "allocation_snapshot_record")
public class AllocationSnapshotRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long investorId;

    @Column(nullable = false)
    private LocalDateTime snapshotDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "totalPortfolioValue must be > 0")
    @Column(nullable = false)
    private Double totalPortfolioValue;

    @Lob
    @Column(nullable = false)
    private String allocationJson;

    @PrePersist
    protected void onCreate() {
        if (totalPortfolioValue == null || totalPortfolioValue <= 0) {
            throw new IllegalArgumentException("totalPortfolioValue must be > 0");
        }
        if (snapshotDate == null) {
            snapshotDate = LocalDateTime.now();
        }
    }

    // Constructors
    public AllocationSnapshotRecord() {}

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public LocalDateTime getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(LocalDateTime snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public Double getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public void setTotalPortfolioValue(Double totalPortfolioValue) {
        if (totalPortfolioValue <= 0) {
            throw new IllegalArgumentException("totalPortfolioValue must be > 0");
        }
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public String getAllocationJson() {
        return allocationJson;
    }

    public void setAllocationJson(String allocationJson) {
        this.allocationJson = allocationJson;
    }
}
