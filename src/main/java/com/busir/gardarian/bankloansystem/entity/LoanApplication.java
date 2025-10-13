package com.busir.gardarian.bankloansystem.entity;

import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationFinalDecision;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationStatus;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {
    private Long id;
    private Long userId;
    private Long managerId;
    private BigDecimal loanAmount;
    private Integer loanTerm;
    private Long purposeId;
    private String description;
    private LoanApplicationStatus status;
    private Integer scoring_score;
    private BigDecimal interestRate;
    private LoanApplicationFinalDecision finalDecision;
    private String decisionComment;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
