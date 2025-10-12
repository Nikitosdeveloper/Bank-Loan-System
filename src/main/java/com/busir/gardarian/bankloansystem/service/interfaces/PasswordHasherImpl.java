package com.busir.gardarian.bankloansystem.service.interfaces;

public interface PasswordHasherImpl {
    public String hashPassword(String password);
    public boolean verifyPassword(String password, String hashedPassword);
}
