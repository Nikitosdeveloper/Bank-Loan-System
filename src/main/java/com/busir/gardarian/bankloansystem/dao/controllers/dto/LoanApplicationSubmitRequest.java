package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationForm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanApplicationSubmitRequest {
    @Positive
    private BigDecimal loanAmount;
    @Positive
    private Integer loanTerm;
    @NotNull
    private Long purposeId;
    private String description;

    public static LoanApplicationForm createLoanApplicationForm(LoanApplicationSubmitRequest request, Long userId) {
        LoanApplicationForm loanApplicationForm = new LoanApplicationForm();
        loanApplicationForm.setUserId(userId);
        loanApplicationForm.setLoanAmount(request.getLoanAmount());
        loanApplicationForm.setLoanTerm(request.getLoanTerm());
        loanApplicationForm.setPurposeId(request.getPurposeId());
        loanApplicationForm.setDescription(request.getDescription());

        return loanApplicationForm;
    }
}
