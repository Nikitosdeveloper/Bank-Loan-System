package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationFinalDecision;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record LoanApplicationInfo(
        Long id,
        Long userId,
        Long managerId,
        BigDecimal loanAmount,
        Integer loanTerm,
        Long purposeId,
        String description,
        LoanApplicationStatus status,
        Integer scoring_score,
        BigDecimal interestRate,
        LoanApplicationFinalDecision finalDecision,
        String decisionComment,
        Timestamp createdAt,
        Timestamp updatedAt
) {
    public static LoanApplicationInfo from(LoanApplication loanApplication) {
        return new LoanApplicationInfo(loanApplication.getId(),
                loanApplication.getUserId(),
                loanApplication.getManagerId(),
                loanApplication.getLoanAmount(),
                loanApplication.getLoanTerm(),
                loanApplication.getPurposeId(),
                loanApplication.getDescription(),
                loanApplication.getStatus(),
                loanApplication.getScoring_score(),
                loanApplication.getInterestRate(),
                loanApplication.getFinalDecision(),
                loanApplication.getDecisionComment(),
                loanApplication.getCreatedAt(),
                loanApplication.getUpdatedAt());
    }
}
