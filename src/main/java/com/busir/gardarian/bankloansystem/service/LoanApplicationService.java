package com.busir.gardarian.bankloansystem.service;

import com.busir.gardarian.bankloansystem.entity.CreditPolicies;
import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationStatus;
import com.busir.gardarian.bankloansystem.service.dto.CreditPolicySetting;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationForm;
import com.busir.gardarian.bankloansystem.service.exception.LoanPurposeNotFound;
import com.busir.gardarian.bankloansystem.service.exception.NoActiveCreditPolicy;
import com.busir.gardarian.bankloansystem.service.exception.UserNotFoundException;
import com.busir.gardarian.bankloansystem.service.interfaces.CreditPolicyRepositoryImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanApplicationRepositoryImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanPurposeRepositoryImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@RequiredArgsConstructor
@Service
public class LoanApplicationService {
    private final LoanPurposeRepositoryImpl loanPurposeRepository;
    private final UserRepositoryImpl userRepository;
    private final CreditPolicyRepositoryImpl creditPolicyRepository;
    private final ScoreCalculatingService scoreCalculatingService;
    private final LoanApplicationRepositoryImpl loanApplicationRepository;

    public Long submitLoanApplication(LoanApplicationForm loanApplicationForm) {

        LoanApplication loanApplication = new LoanApplication();

        if (userRepository.findById(loanApplicationForm.getUserId()) == null){
            throw new UserNotFoundException("User with that id is not found");
        }
        if (loanPurposeRepository.findById(loanApplicationForm.getPurposeId()) == null) {
            throw new LoanPurposeNotFound("Purpose for loan not found");
        }

        CreditPolicies creditPolicies = creditPolicyRepository.getCreditActualPolicies();
        if (creditPolicies == null) {
            throw new NoActiveCreditPolicy("No active credit policy found");
        }
        try {
            loanApplication = loanApplicationProcessing(loanApplicationForm, creditPolicies);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JsonException: " + e);
        }

        loanApplication =  loanApplicationRepository.save(loanApplication);

        return loanApplication.getId();
    }

    private LoanApplication loanApplicationProcessing(LoanApplicationForm loanApplicationForm, CreditPolicies creditPolicies) throws JsonProcessingException {
        LoanApplication loanApplication = new LoanApplication();

        loanApplication.setUserId(loanApplicationForm.getUserId());
        loanApplication.setPurposeId(loanApplicationForm.getPurposeId());
        loanApplication.setLoanAmount(loanApplicationForm.getLoanAmount());
        loanApplication.setLoanTerm(loanApplicationForm.getLoanTerm());
        loanApplication.setDescription(loanApplicationForm.getDescription());
        loanApplication.setStatus(LoanApplicationStatus.PENDING);

        loanApplication.setScoring_score(scoreCalculatingService.calculateScoringScore(loanApplicationForm, creditPolicies));
        loanApplication.setInterestRate(creditPolicies.getBaseInterestRate());

        loanApplication.setCreatedAt(Timestamp.from(Instant.now()));
        loanApplication.setUpdatedAt(Timestamp.from(Instant.now()));

        return loanApplication;
    }
}
