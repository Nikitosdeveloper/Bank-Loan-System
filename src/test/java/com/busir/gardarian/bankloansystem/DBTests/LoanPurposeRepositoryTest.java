package com.busir.gardarian.bankloansystem.DBTests;

import com.busir.gardarian.bankloansystem.entity.LoanPurpose;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanPurposeRepositoryImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoanPurposeRepositoryTest {
    @Autowired
    private LoanPurposeRepositoryImpl repository;

    @Test
    @Order(1)
    public void createTest(){
        LoanPurpose loanPurpose = new LoanPurpose(
                null,
                "money",
                "Наличные",
                "Ну блин, ну прост очень нужны деньги",
                "personal"
        );

        repository.save(loanPurpose);
    }

    @Test
    @Order(2)
    public void updateTest(){
        LoanPurpose loanPurpose = new LoanPurpose(
                1L,
                "money",
                "Денежные средства",
                "Если человеку нужны денежные средства, без указания причины",
                "personal"
        );

        repository.save(loanPurpose);
    }

    @Test
    @Order(3)
    public void readByCodeTest(){
        LoanPurpose test = new LoanPurpose(
                1L,
                "money",
                "Денежные средства",
                "Если человеку нужны денежные средства, без указания причины",
                "personal"
        );

        LoanPurpose lp = repository.findByCode(test.getCode());

        assertEquals(test, lp);
    }

}
