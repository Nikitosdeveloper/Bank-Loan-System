package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.service.dto.CreditPolicyForm;

import java.math.BigDecimal;

public record CreditPolicyRequest (
         String policyName,
         BigDecimal minAmount,
         BigDecimal maxAmount,
         Integer minTerm,
         Integer maxTerm,
         Integer minAge,
         Integer maxAge,
         BigDecimal minIncome,
         BigDecimal baseInterestRate,
         String scoringRules
){
    public CreditPolicyForm create (Long adminId){

        return new CreditPolicyForm(
                this.policyName,
                this.minAmount,
                this.maxAmount,
                this.minTerm,
                this.maxTerm,
                this.minAge,
                this.maxAge,
                this.minIncome,
                this.baseInterestRate,
                adminId,
                this.scoringRules
        );
    }
}
