package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.service.dto.UserRegistrationForm;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String fullName;
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$")
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
