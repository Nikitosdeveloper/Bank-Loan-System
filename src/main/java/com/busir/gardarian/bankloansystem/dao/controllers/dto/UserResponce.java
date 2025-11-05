package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.entity.enums.Role;
import com.busir.gardarian.bankloansystem.service.dto.UserInfo;

public record UserResponce(
         Long id,
         String email,
         Role role,
         String fullName,
         String phone,
         Boolean isActive
) {
    public static UserResponce from(UserInfo user) {
        return new UserResponce(user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getFullName(),
                user.getPhone(),
                user.getIsActive());
    }
}
