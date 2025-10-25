package com.busir.gardarian.bankloansystem.dao.infrostructure.security;

import com.busir.gardarian.bankloansystem.service.interfaces.PasswordHasherImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("devDAO")
@Primary
public class BCryptPasswordHasher implements PasswordHasherImpl {
    private final PasswordEncoder encoder;

    @Override
    public String hashPassword(String password) {
        if(password == null || password.isBlank()) {
            throw new IllegalArgumentException("password is null or empty");
        }
        return encoder.encode(password);
    }

    @Override
    public boolean verifyPassword(String password, String hashedPassword) {
        if (password == null || hashedPassword == null) {
            return false;
        }
        try {
            return encoder.matches(password, hashedPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
