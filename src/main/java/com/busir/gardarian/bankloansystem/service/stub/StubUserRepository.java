package com.busir.gardarian.bankloansystem.service.stub;

import com.busir.gardarian.bankloansystem.entity.User;
import com.busir.gardarian.bankloansystem.entity.enums.Role;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;

@Repository
@Profile("devService")
public class StubUserRepository implements UserRepositoryImpl {

    @Override
    public User findById(Long id) {
        return new User(
                3L,
                "testemail@gmail.com",
                "passwordPassword",
                Role.CLIENT,
                "testName",
                "+7885642134",
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()),
                true
        );
    }

    @Override
    public User findByUsername(String username) {
        return new User(
                5L,
                "testemail1@gmail.com",
                "passwordPassword1",
                Role.CLIENT,
                "NAME",
                "+7885656134",
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()),
                true
        );
    }

    @Override
    public User findByEmail(String email) {
        return new User(
                10L,
                "EMAIL@gmail.com",
                "passwordPassword2",
                Role.CLIENT,
                "testName1",
                "+7885642155",
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()),
                true
        );
    }

    @Override
    public User save(User user) {
        user.setId(10L);
        return user;
    }

    @Override
    public Boolean delete(Long id) {
        return true;
    }
}
