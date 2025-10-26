package com.busir.gardarian.bankloansystem.dao.infrostructure.security.dto;

import lombok.Data;

@Data
public class JwtAuthenticationDto {
    private String accessToken;
    private String refreshToken;
}
