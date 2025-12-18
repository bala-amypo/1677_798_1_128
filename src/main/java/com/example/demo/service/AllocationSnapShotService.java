import java.time.LocalDateTime;
import java.util.Map;

public class AllocationSnapshot {
    private Long id;
    private Long investorId;
    private LocalDateTime createdAt;
    private Map<String, Double> allocationPercentages;
}

public class AllocationAlert {
    private Long id;
    private Long investorId;
    private String assetClass;
    private double actualPercentage;
    private double rulePercentage;
    private double thresholdExceeded; 
    private LocalDateTime createdAt;
}
