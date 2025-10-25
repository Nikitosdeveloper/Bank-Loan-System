package com.busir.gardarian.bankloansystem.service.stub;

import com.busir.gardarian.bankloansystem.service.interfaces.PasswordHasherImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"devService", "devDAO"})
public class StubPasswordHasher implements PasswordHasherImpl {
    @Override
    public String hashPassword(String password) {
        return password;
    }

    @Override
    public boolean verifyPassword(String password, String hashedPassword) {
        return password.equals(hashedPassword);
    }
}

