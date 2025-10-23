package com.busir.gardarian.bankloansystem.DBTests;

import com.busir.gardarian.bankloansystem.entity.CreditPolicies;
import com.busir.gardarian.bankloansystem.service.interfaces.CreditPolicyRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
public class CreditPolicyRepositoryTest {
    @Autowired
    private CreditPolicyRepositoryImpl repository;

    @Order(1)
    @Test
    public void creditPolicySaveTest(){
        CreditPolicies creditPolicies = new CreditPolicies(
                null,
                "Base Policy",
                new BigDecimal(300.00),
                new BigDecimal(30000.00),
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

        creditPolicies = repository.save(creditPolicies);

        CreditPolicies ps = repository.getCreditActualPolicies();
    }
}
