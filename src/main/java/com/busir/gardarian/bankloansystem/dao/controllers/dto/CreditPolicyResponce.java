package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.entity.CreditPolicies;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record CreditPolicyResponce(
         Long id,
         String policyName,
         BigDecimal minAmount,
         BigDecimal maxAmount,
         Integer minTerm,
         Integer maxTerm,
         Integer minAge,
         Integer maxAge,
         BigDecimal minIncome,
         BigDecimal baseInterestRate,
         Boolean isActive,
         Long createdById,
         Timestamp createdAt,
         Timestamp updatedAt,
         String scoringRules
) {
    public static CreditPolicyResponce from(CreditPolicies creditPolicies) {
        return new CreditPolicyResponce(
                creditPolicies.getId(),
                creditPolicies.getPolicyName(),
                creditPolicies.getMinAmount(),
                creditPolicies.getMaxAmount(),
                creditPolicies.getMinTerm(),
                creditPolicies.getMaxTerm(),
                creditPolicies.getMinAge(),
                creditPolicies.getMaxAge(),
                creditPolicies.getMinIncome(),
                creditPolicies.getBaseInterestRate(),
                creditPolicies.getIsActive(),
                creditPolicies.getCreatedById(),
                creditPolicies.getCreatedAt(),
                creditPolicies.getUpdatedAt(),
                creditPolicies.getScoringRules()
        );
    }
}
