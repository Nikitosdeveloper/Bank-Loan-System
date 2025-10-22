package com.busir.gardarian.bankloansystem.service.stub;

import com.busir.gardarian.bankloansystem.entity.CreditPolicies;
import com.busir.gardarian.bankloansystem.service.interfaces.CreditPolicyRepositoryImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Profile("devService")
public class StubCreditPolicyRepository implements CreditPolicyRepositoryImpl {
    @Override
    public CreditPolicies getCreditActualPolicies() {
        return new CreditPolicies(
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
                1L,
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
    }

    @Override
    public CreditPolicies getById(Long id) {
        return new CreditPolicies(
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
                1L,
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
    }

    @Override
    public List<CreditPolicies> getAll() {
        return List.of(new CreditPolicies(
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
                1L,
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
        ));
    }

    @Override
    public CreditPolicies save(CreditPolicies creditPolicies) {
        creditPolicies.setId(2L);
        return creditPolicies;
    }

    @Override
    public Boolean delete(Long id) {
        return true;
    }
}
