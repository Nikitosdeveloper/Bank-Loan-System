package com.busir.gardarian.bankloansystem.service.exception;

public class NoActiveCreditPolicy extends RuntimeException {
    public NoActiveCreditPolicy(String message) {
        super(message);
    }
}
