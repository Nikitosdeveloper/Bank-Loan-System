package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.entity.LoanPurpose;
import lombok.Data;

@Data
public class LoanPurposeResponce {
    private Long id;
    private String nameRu;
    private String descriptionRu;

    public static LoanPurposeResponce from(LoanPurpose loanPurpose) {
        LoanPurposeResponce loanPurposeResponce = new LoanPurposeResponce();
        loanPurposeResponce.setId(loanPurpose.getId());
        loanPurposeResponce.setNameRu(loanPurpose.getNameRu());
        loanPurposeResponce.setDescriptionRu(loanPurpose.getDescriptionRu());
        return loanPurposeResponce;
    }
}
