package com.busir.gardarian.bankloansystem.DBTests;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.LoanApplicationDB;
import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import com.busir.gardarian.bankloansystem.entity.LoanPurpose;
import com.busir.gardarian.bankloansystem.entity.User;
import com.busir.gardarian.bankloansystem.service.LoanApplicationService;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationForm;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanApplicationRepositoryImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanPurposeRepositoryImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class LoanApplicationRepositoryTest {
    @Autowired
    private LoanApplicationRepositoryImpl loanApplicationRepository;
    @Autowired
    private LoanApplicationService loanApplicationService;
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private LoanPurposeRepositoryImpl loanPurposeRepository;

    @Test
    @Order(1)
    public void submitLoanApplicationTest(){

        User user = userRepository.findByEmail("yakovnikita01@gmail.com");
        LoanPurpose purpose = loanPurposeRepository.findByCode("money");

        LoanApplicationForm loanApplicationForm = new LoanApplicationForm(
                user.getId(),
                new BigDecimal(5000),
                12,
                purpose.getId(),
                "Я хочу бабла"
        );

        loanApplicationService.submitLoanApplication(loanApplicationForm);
    }

    @Test
    @Order(2)
    public void clearALL(){
        List<Long> idList =  loanApplicationRepository.getAll().stream()
                .map(LoanApplication::getId)
                .toList();
        for(Long id : idList){
            loanApplicationRepository.delete(id);
        }
    }
}
