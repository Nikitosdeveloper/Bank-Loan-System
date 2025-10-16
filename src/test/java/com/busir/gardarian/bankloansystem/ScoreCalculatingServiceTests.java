package com.busir.gardarian.bankloansystem;

import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import com.busir.gardarian.bankloansystem.entity.CreditPolicies;
import com.busir.gardarian.bankloansystem.service.ScoreCalculatingService;
import com.busir.gardarian.bankloansystem.service.dto.CreditPolicySetting;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationForm;
import com.busir.gardarian.bankloansystem.service.interfaces.ClientAdditionalInfoRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ScoreCalculatingServiceTests {
    @MockitoBean
    private  ClientAdditionalInfoRepositoryImpl repository;
    @Autowired
    private ScoreCalculatingService service;

    @Test
    void calculateScoreTest() throws JsonProcessingException {

        LoanApplicationForm form = new LoanApplicationForm(
                1L,
                new BigDecimal(300),
                12,
                1L,
                "To car"
        );



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
        ClientAdditionalInfo info = new ClientAdditionalInfo(
                1L,
                1L,
                32,
                new BigDecimal(1200),
                new BigDecimal(810),
                new BigDecimal(2010)

        );

        when(repository.getClientAdditionalInfoByUserId(form.getUserId())).thenReturn(info);

        Integer score = service.calculateScoringScore(form, creditPolicies);

        assertEquals(86, score);
    }
}
