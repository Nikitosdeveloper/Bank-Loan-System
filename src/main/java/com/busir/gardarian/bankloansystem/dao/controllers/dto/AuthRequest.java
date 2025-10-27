package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
}
