package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;
}
