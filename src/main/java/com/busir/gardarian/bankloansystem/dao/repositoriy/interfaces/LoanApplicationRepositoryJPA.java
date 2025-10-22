package com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.LoanApplicationDB;
import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepositoryJPA extends JpaRepository<LoanApplicationDB, Long> {
    List<LoanApplicationDB> findByManagerId(Integer manager_id);

    List<LoanApplicationDB> findByUserId(Long userId);

    List<LoanApplicationDB> findByManagerIsNull();
}
