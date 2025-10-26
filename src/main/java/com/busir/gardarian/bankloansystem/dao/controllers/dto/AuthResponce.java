package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.dao.infrostructure.security.dto.JwtAuthenticationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponce {
    String AccessToken;
    String RefreshToken;

    public static AuthResponce from(JwtAuthenticationDto authenticationDto) {
        return new AuthResponce(authenticationDto.getAccessToken(), authenticationDto.getRefreshToken());
    }
}
