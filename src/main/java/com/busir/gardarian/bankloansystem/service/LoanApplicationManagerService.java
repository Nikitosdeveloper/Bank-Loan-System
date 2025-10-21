package com.busir.gardarian.bankloansystem.service;

import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationFinalDecision;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationStatus;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationDecision;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanApplicationRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanApplicationManagerService {
    private final LoanApplicationRepositoryImpl loanApplicationRepository;

    public List<LoanApplication> getFreeLoanApplications(){
        return loanApplicationRepository.getFreeLoanApplications();
    }

    public void decision(LoanApplicationDecision decision){
        LoanApplication loanApplication = loanApplicationRepository.getById(decision.getLoanApplicationId());

        loanApplication.setManagerId(decision.getManagerId());
        loanApplication.setFinalDecision(decision.getFinalDecision());
        loanApplication.setDecisionComment(decision.getDecisionComment());

        if (decision.getFinalDecision() == LoanApplicationFinalDecision.REJECTED){
            loanApplication.setStatus(LoanApplicationStatus.REJECTED);
        }

        switch (decision.getFinalDecision()){
            case REJECTED:
                loanApplication.setStatus(LoanApplicationStatus.REJECTED);
                break;
            case APPROVED:
                loanApplication.setStatus(LoanApplicationStatus.APPROVED);
                break;
            case PENDING:
                if (decision.getRequiredDocument())
                    loanApplication.setStatus(LoanApplicationStatus.REQUIRES_DOCS);
                else
                    loanApplication.setStatus(LoanApplicationStatus.UNDER_REVIEW);
        }

        loanApplicationRepository.save(loanApplication);
    }
}
