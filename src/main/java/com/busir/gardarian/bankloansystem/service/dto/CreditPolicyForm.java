package com.busir.gardarian.bankloansystem.service.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreditPolicyForm {
    private String policyName;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minTerm;
    private Integer maxTerm;
    private Integer minAge;
    private Integer maxAge;
    private BigDecimal min_income;
    private BigDecimal baseInterestRate;
    private Long adminId;
    private String scoringRules;
}
