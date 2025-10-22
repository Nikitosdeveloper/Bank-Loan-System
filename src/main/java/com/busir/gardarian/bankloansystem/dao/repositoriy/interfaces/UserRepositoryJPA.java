package com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.UserDB;
import com.busir.gardarian.bankloansystem.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryJPA extends JpaRepository<UserDB, Long> {
    List<UserDB> findByRole(Role role);
    Optional<UserDB> findByFullName(String username);
    Optional<UserDB> findByEmail(String email);

}
