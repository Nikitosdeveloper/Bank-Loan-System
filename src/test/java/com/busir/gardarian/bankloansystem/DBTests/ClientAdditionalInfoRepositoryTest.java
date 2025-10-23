package com.busir.gardarian.bankloansystem.DBTests;

import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import com.busir.gardarian.bankloansystem.service.interfaces.ClientAdditionalInfoRepositoryImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientAdditionalInfoRepositoryTest {
    @Autowired
    private ClientAdditionalInfoRepositoryImpl repo;
    @Autowired
    private UserRepositoryImpl userRepo;

    @Test
    @Order(1)
    public void createTest() {

        Long userId = userRepo.findByEmail("yakovnikita01@gmail.com").getId();

        ClientAdditionalInfo ci = new ClientAdditionalInfo(
                null,
                userId,
                20,
                new BigDecimal(3000),
                new BigDecimal(300),
                new BigDecimal(3300)
        );

        ci = repo.saveClientAdditionalInfo(ci);

        ClientAdditionalInfo ci1 = repo.getClientAdditionalInfoById(ci.getId());

        assertNotNull(ci1);
    }

    @Test
    @Order(2)
    public void deleteTest() {
        Long id = repo.getClientAdditionalInfoByUserId(userRepo.findByEmail("yakovnikita01@gmail.com").getId()).getId();

        repo.deleteClientAdditionalInfo(id);

        ClientAdditionalInfo ci1 = repo.getClientAdditionalInfoByUserId(userRepo.findByEmail("yakovnikita01@gmail.com").getId());

        assertNull(ci1);
    }
}

