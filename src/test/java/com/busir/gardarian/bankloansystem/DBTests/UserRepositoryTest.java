package com.busir.gardarian.bankloansystem.DBTests;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.UserDB;
import com.busir.gardarian.bankloansystem.entity.User;
import com.busir.gardarian.bankloansystem.service.AccountService;
import com.busir.gardarian.bankloansystem.service.dto.UserProfileDto;
import com.busir.gardarian.bankloansystem.service.dto.UserRegistrationForm;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRepositoryImpl userRepository;

    @Order(2)
    @Test
    public void registerUserTest() {
        UserRegistrationForm form = new UserRegistrationForm(
                "yakovnikita01@gmail.com",
                "1111",
                "Яковлев Никита Евгеньевич",
                "+375297245696"
        );

        UserProfileDto dto =  accountService.register(form);

        assertNotNull(dto);

    }

    @Test
    @Order(1)
    public void deleteTest(){

        List<User> users = userRepository.findAll();
        User user = users.get(0);

        Long id = user.getId();

        userRepository.delete(id);

        User user1 = userRepository.findById(id);
        assertNull(user1);
    }

    @Test
    @Order(3)
    public void loginTest(){
        String login = "yakovnikita01@gmail.com";
        String password = "1111";

        UserProfileDto dto = accountService.login(login, password);
        assertNotNull(dto);
    }

}
