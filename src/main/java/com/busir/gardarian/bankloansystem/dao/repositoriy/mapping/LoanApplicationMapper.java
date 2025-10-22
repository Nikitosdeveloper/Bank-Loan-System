package com.busir.gardarian.bankloansystem.dao.repositoriy.mapping;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.LoanApplicationDB;
import com.busir.gardarian.bankloansystem.dao.repositoriy.models.LoanPurposeDB;
import com.busir.gardarian.bankloansystem.dao.repositoriy.models.UserDB;
import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LoanApplicationMapper {
    private final EntityManager em;

    public List<LoanApplication> toEntity(List<LoanApplicationDB> loanApplicationDBList) {
        return loanApplicationDBList.stream()
                .map(this::toEntity)
                .toList();
    }

    private List<LoanApplicationDB> toDB(List<LoanApplication> loanApplicationList) {
        return loanApplicationList.stream()
                .map(this::toDB)
                .toList();
    }

    public LoanApplication toEntity(LoanApplicationDB loanApplicationDB) {
        LoanApplication loanApplication = new LoanApplication();

        if(loanApplicationDB.getId() != null)
            loanApplication.setId(Long.valueOf(loanApplicationDB.getId()));

        loanApplication.setUserId(Long.valueOf(loanApplicationDB.getUser().getId()));

        if(loanApplicationDB.getManager() != null)
            loanApplication.setManagerId(Long.valueOf(loanApplicationDB.getManager().getId()));

        loanApplication.setLoanAmount(loanApplicationDB.getLoanAmount());
        loanApplication.setLoanTerm(loanApplicationDB.getLoanTerm());
        loanApplication.setPurposeId(Long.valueOf(loanApplicationDB.getPurpose().getId()));
        loanApplication.setDescription(loanApplicationDB.getDescription());
        loanApplication.setStatus(loanApplicationDB.getStatus());
        loanApplication.setScoring_score(loanApplicationDB.getScoringScore());
        loanApplication.setInterestRate(loanApplicationDB.getInterestRate());
        loanApplication.setFinalDecision(loanApplicationDB.getFinalDecision());
        loanApplication.setDecisionComment(loanApplicationDB.getDecisionComment());
        loanApplication.setCreatedAt(Timestamp.valueOf(loanApplicationDB.getCreatedAt()));
        loanApplication.setUpdatedAt(Timestamp.valueOf(loanApplicationDB.getUpdatedAt()));
        
        return loanApplication;
    }

    public LoanApplicationDB toDB(LoanApplication loanApplication) {
        LoanApplicationDB loanApplicationDB = new LoanApplicationDB();

        if(loanApplication.getId() != null)
            loanApplicationDB.setId(Math.toIntExact(loanApplication.getId()));

        UserDB userDB = em.getReference(UserDB.class, loanApplication.getUserId());
        loanApplicationDB.setUser(userDB);

        if(loanApplication.getManagerId() != null){
            UserDB managerDB = em.getReference(UserDB.class, loanApplication.getManagerId());
            loanApplicationDB.setManager(managerDB);
        }

        loanApplicationDB.setLoanAmount(loanApplication.getLoanAmount());
        loanApplicationDB.setLoanTerm(loanApplication.getLoanTerm());

        LoanPurposeDB purposeDB = em.getReference(LoanPurposeDB.class, loanApplication.getPurposeId());
        loanApplicationDB.setPurpose(purposeDB);

        loanApplicationDB.setDescription(loanApplication.getDescription());
        loanApplicationDB.setStatus(loanApplication.getStatus());
        loanApplicationDB.setScoringScore(loanApplication.getScoring_score());
        loanApplicationDB.setInterestRate(loanApplication.getInterestRate());
        loanApplicationDB.setFinalDecision(loanApplication.getFinalDecision());
        loanApplicationDB.setDecisionComment(loanApplication.getDecisionComment());
        loanApplicationDB.setCreatedAt(loanApplication.getCreatedAt().toLocalDateTime());
        loanApplicationDB.setUpdatedAt(loanApplication.getUpdatedAt().toLocalDateTime());

        return loanApplicationDB;
    }
}
