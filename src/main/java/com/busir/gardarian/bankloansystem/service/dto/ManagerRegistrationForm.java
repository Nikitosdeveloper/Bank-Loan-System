package com.busir.gardarian.bankloansystem.service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ManagerRegistrationForm {
    private String email;
    private String fullName;
    private String phone;
}
