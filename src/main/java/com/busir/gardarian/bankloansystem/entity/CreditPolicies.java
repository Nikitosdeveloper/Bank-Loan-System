    package com.busir.gardarian.bankloansystem.entity;

    import lombok.*;

    import java.math.BigDecimal;
    import java.sql.Timestamp;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode
    public class CreditPolicies {
        private Long id;
        private String policyName;
        private BigDecimal minAmount;
        private BigDecimal maxAmount;
        private Integer minTerm;
        private Integer maxTerm;
        private Integer minAge;
        private Integer maxAge;
        private BigDecimal minIncome;
        private BigDecimal baseInterestRate;
        private Boolean isActive;
        private Long createdById;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private String scoringRules;
    }
