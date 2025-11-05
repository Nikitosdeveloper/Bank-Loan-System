package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationFinalDecision;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationDecision;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record DecisionRequest(
         @NotNull
         Long loanApplicationId,
         @NotNull
         LoanApplicationFinalDecision finalDecision,
         String decisionComment,
         Boolean requiredDocument
) {
    public LoanApplicationDecision createLoanApplicationDecision(Long managerId) {
        return new LoanApplicationDecision(
                this.loanApplicationId,
                managerId,
                this.finalDecision,
                this.decisionComment,
                this.requiredDocument != null && this.requiredDocument
        );
    }
}
