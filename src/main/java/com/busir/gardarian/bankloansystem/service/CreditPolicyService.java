package com.busir.gardarian.bankloansystem.service;

import com.busir.gardarian.bankloansystem.entity.CreditPolicies;
import com.busir.gardarian.bankloansystem.service.dto.CreditPolicyForm;
import com.busir.gardarian.bankloansystem.service.interfaces.CreditPolicyRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreditPolicyService {

    private final CreditPolicyRepositoryImpl creditPolicyRepository;

    public Boolean createCreditPolicy(CreditPolicyForm creditPolicyForm) {

        CreditPolicies creditPolicies = cretePolicyFromForm(creditPolicyForm);

        creditPolicies.setIsActive(false);

        creditPolicies.setCreatedAt(Timestamp.from(Instant.now()));
        creditPolicies.setCreatedAt(Timestamp.from(Instant.now()));


        creditPolicyRepository.save(creditPolicies);

        return true;
    }

    public List<CreditPolicies> getAllCreditPolicies() {
        return creditPolicyRepository.getAll();
    }

    public Boolean updateCreditPolicy(CreditPolicyForm creditPolicyForm, Long creditPolicyId) {
        CreditPolicies creditPolicies = creditPolicyRepository.getById(creditPolicyId);

        creditPolicies.setPolicyName(creditPolicyForm.getPolicyName());
        creditPolicies.setMinAmount(creditPolicyForm.getMinAmount());
        creditPolicies.setMaxAmount(creditPolicyForm.getMaxAmount());
        creditPolicies.setMinTerm(creditPolicyForm.getMinTerm());
        creditPolicies.setMaxTerm(creditPolicyForm.getMaxTerm());
        creditPolicies.setMinAge(creditPolicyForm.getMinAge());
        creditPolicies.setMaxAge(creditPolicyForm.getMaxAge());
        creditPolicies.setMin_income(creditPolicyForm.getMin_income());
        creditPolicies.setBaseInterestRate(creditPolicyForm.getBaseInterestRate());
        creditPolicies.setCreatedById(creditPolicyForm.getAdminId());
        creditPolicies.setScoringRules(creditPolicyForm.getScoringRules());

        creditPolicyRepository.save(creditPolicies);

        return true;

    }

    public boolean setActiveCreditPolicy(Long creditPolicyId) {
        List<CreditPolicies> creditPolicies = creditPolicyRepository.getAll();

        for (CreditPolicies creditPolicy : creditPolicies) {
            if (creditPolicy.getId().equals(creditPolicyId)) {
                creditPolicy.setIsActive(true);
                creditPolicyRepository.save(creditPolicy);
            }else if(creditPolicy.getIsActive()){
                creditPolicy.setIsActive(false);
                creditPolicyRepository.save(creditPolicy);
            }
        }

        return true;
    }

    public void createBaseCreditPolicy(Long adminId) {
        CreditPolicies creditPolicies = new CreditPolicies(
                1L,
                "Base Policy",
                new BigDecimal(300),
                new BigDecimal(30000),
                3,
                36,
                18,
                80,
                new BigDecimal( 500),
                new BigDecimal("18.5"),
                true,
                adminId,
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()),
                """
                            {
                                "ageSetting": {
                                    "minAge": 21,
                                    "maxAge": 65,
                                    "weight": {
                                        "18-25": 10,
                                        "26-35": 20,
                                        "36-50": 25,
                                        "51-65": 15
                                    },
                                    "maxPoint": 25
                                },
                                "incomeSetting": {
                                    "minIncome": 500,
                                    "weight": {
                                        "500-1000": 15,
                                        "1001-2000": 25,
                                        "2001+": 35
                                    },
                                    "maxPoint": 35
                                }
                            }
                            """
        );

        creditPolicyRepository.save(creditPolicies);
    }

    private CreditPolicies cretePolicyFromForm(CreditPolicyForm creditPolicyForm) {
        CreditPolicies creditPolicies = new CreditPolicies();
        creditPolicies.setPolicyName(creditPolicyForm.getPolicyName());
        creditPolicies.setMinAmount(creditPolicyForm.getMinAmount());
        creditPolicies.setMaxAmount(creditPolicyForm.getMaxAmount());
        creditPolicies.setMinTerm(creditPolicyForm.getMinTerm());
        creditPolicies.setMaxTerm(creditPolicyForm.getMaxTerm());
        creditPolicies.setMinAge(creditPolicyForm.getMinAge());
        creditPolicies.setMaxAge(creditPolicyForm.getMaxAge());
        creditPolicies.setMin_income(creditPolicyForm.getMin_income());
        creditPolicies.setBaseInterestRate(creditPolicyForm.getBaseInterestRate());

        creditPolicies.setCreatedById(creditPolicyForm.getAdminId());

        creditPolicies.setScoringRules(creditPolicyForm.getScoringRules());

        return creditPolicies;
    }
}
