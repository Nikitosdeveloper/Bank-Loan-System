package com.busir.gardarian.bankloansystem.service.dto;

import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationFinalDecision;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoanApplicationDecision {
    private Long loanApplicationId;
    private Long managerId;
    private LoanApplicationFinalDecision finalDecision;
    private String decisionComment;
    private Boolean requiredDocument = false;
}
