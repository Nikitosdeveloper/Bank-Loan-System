package com.busir.gardarian.bankloansystem.entity;

import com.busir.gardarian.bankloansystem.entity.enums.Role;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private String email;
    private String passwordHash;
    private Role role;
    private String fullName;
    private String phone;
    private Timestamp created;
    private Timestamp updated;
    private Boolean isActive;
}
