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
    public Long id;
    public String policyName;
    public BigDecimal minAmount;
    public BigDecimal maxAmount;
    public Integer minTerm;
    public Integer maxTerm;
    public Integer minAge;
    public Integer maxAge;
    public BigDecimal min_income;
    public BigDecimal baseInterestRate;
    public Boolean isActive;
    public Long createdById;
    public Timestamp createdAt;
    public Timestamp updatedAt;
    public String scoringRules;
}
