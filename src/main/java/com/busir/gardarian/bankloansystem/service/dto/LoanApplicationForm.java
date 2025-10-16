package com.busir.gardarian.bankloansystem.service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationForm {
    private Long userId;
    private BigDecimal loanAmount;
    private Integer loanTerm;
    private Long purposeId;
    private String description;
}
