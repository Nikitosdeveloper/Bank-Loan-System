package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationFinalDecision;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationStatus;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationResult;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

public record LoanApplicationStatusResponce
        (
                Long id,
                LoanApplicationFinalDecision finalDecision,
                String decisionComment,
                BigDecimal loanAmount,
                Integer loanTerm
        )
{
        public static LoanApplicationStatusResponce from(LoanApplicationResult result) {
                return new LoanApplicationStatusResponce(
                        result.getId(),
                        result.getFinalDecision(),
                        result.getDecisionComment(),
                        result.getLoanAmount(),
                        result.getLoanTerm()
                );
        }
}
