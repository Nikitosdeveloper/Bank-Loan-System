package com.busir.gardarian.bankloansystem.service.exception;

public class UserIsNotActive extends RuntimeException {
    public UserIsNotActive(String message) {
        super(message);
    }
}
