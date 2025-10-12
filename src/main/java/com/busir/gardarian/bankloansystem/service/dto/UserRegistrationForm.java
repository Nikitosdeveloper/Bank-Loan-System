package com.busir.gardarian.bankloansystem.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationForm {
    private String email;
    private String password;
    private String fullName;
    private String phone;
}
