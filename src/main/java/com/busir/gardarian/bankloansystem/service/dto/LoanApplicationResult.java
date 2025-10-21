package com.busir.gardarian.bankloansystem.service.dto;

import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationFinalDecision;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationResult {
    private Long id;
    private LoanApplicationStatus status;
    private LoanApplicationFinalDecision finalDecision;
    private String decisionComment;
    private BigDecimal loanAmount;
    private Integer loanTerm;

    public LoanApplicationResult(LoanApplication loanApplication) {
        this.id = loanApplication.getId();
        this.status = loanApplication.getStatus();
        this.finalDecision = loanApplication.getFinalDecision();
        this.decisionComment = loanApplication.getDecisionComment();
        this.loanAmount = loanApplication.getLoanAmount();
        this.loanTerm = loanApplication.getLoanTerm();
    }
}
