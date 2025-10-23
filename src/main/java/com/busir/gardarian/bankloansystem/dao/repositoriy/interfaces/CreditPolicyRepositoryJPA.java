package com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.CreditPolicyDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditPolicyRepositoryJPA extends JpaRepository<CreditPolicyDB, Long> {
    CreditPolicyDB findByIsActive(boolean active);
}
