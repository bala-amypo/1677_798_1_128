import java.time.LocalDateTime;

public class RebalancingAlertRecord {
    private Long id;
    private Long investorId;
    private String assetClass;
    private double currentPercentage;
    private double targetPercentage;
    private boolean resolved;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
}
