package com.busir.gardarian.bankloansystem.service.dto;

import com.busir.gardarian.bankloansystem.entity.User;
import com.busir.gardarian.bankloansystem.entity.enums.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserInfo {
    private Long id;
    private String email;
    private Role role;
    private String fullName;
    private String phone;
    private Boolean isActive;

    public UserInfo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.fullName = user.getFullName();
        this.phone = user.getPhone();
        this.isActive = user.getIsActive();
    }
}
