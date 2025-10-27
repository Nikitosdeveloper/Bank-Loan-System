package com.busir.gardarian.bankloansystem.dao.infrostructure.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {
    ACCESS_TOKEN("ACCESS_TOKEN"),
    REFRESH_TOKEN("REFRESH_TOKEN");

    private final String name;

    public static TokenType fromString(String name) {
        for (TokenType tokenType : TokenType.values()) {
            if (tokenType.name.equals(name)) {
                return tokenType;
            }
        }
        throw new IllegalArgumentException("Unknown token type: " + name);
    }

}
