package com.busir.gardarian.bankloansystem.dao.repositoriy.mapping;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.LoanPurposeDB;
import com.busir.gardarian.bankloansystem.entity.LoanPurpose;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanPurposeMapper {

    public List<LoanPurpose> toEntity(List<LoanPurposeDB> loanPurposeDBList) {
        return loanPurposeDBList.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<LoanPurposeDB> toDB(List<LoanPurpose> loanPurposeList) {
        return loanPurposeList.stream()
                .map(this::toDB)
                .toList();
    }

    public LoanPurpose toEntity(LoanPurposeDB loanPurposeDB) {
        LoanPurpose loanPurpose = new LoanPurpose();

        if(loanPurposeDB.getId() != null)
            loanPurpose.setId(Long.valueOf(loanPurposeDB.getId()));

        loanPurpose.setCode(loanPurposeDB.getCode());
        loanPurpose.setCategory(loanPurposeDB.getCategory());
        loanPurpose.setNameRu(loanPurposeDB.getNameRu());
        loanPurpose.setDescriptionRu(loanPurposeDB.getDescriptionRu());

        return loanPurpose;
    }

    public LoanPurposeDB toDB(LoanPurpose loanPurpose) {
        LoanPurposeDB loanPurposeDB = new LoanPurposeDB();

        if(loanPurpose.getId() != null)
            loanPurposeDB.setId(Math.toIntExact(loanPurpose.getId()));

        loanPurposeDB.setCode(loanPurpose.getCode());
        loanPurposeDB.setCategory(loanPurpose.getCategory());
        loanPurposeDB.setNameRu(loanPurpose.getNameRu());
        loanPurposeDB.setDescriptionRu(loanPurpose.getDescriptionRu());

        return loanPurposeDB;
    }
}
