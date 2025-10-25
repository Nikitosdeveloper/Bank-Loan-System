package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.service.dto.UserRegistrationForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private String phone;

    public static UserRegistrationForm toForm(RegisterRequest registerRequest) {
        UserRegistrationForm form = new UserRegistrationForm();
        form.setEmail(registerRequest.getEmail());
        form.setPhone(registerRequest.getPhone());
        form.setFullName(registerRequest.getFullName());
        form.setPassword(registerRequest.getPassword());
        return form;
    }
}
