package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.service.dto.ManagerRegistrationForm;

public record CreateManagerRequest(
        String email,
        String fullName,
        String phone
) {
    public ManagerRegistrationForm createManagerRegistrationForm() {
        return new ManagerRegistrationForm(email, fullName, phone);
    }
}
