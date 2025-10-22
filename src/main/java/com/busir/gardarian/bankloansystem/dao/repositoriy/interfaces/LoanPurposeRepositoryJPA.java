package com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.LoanPurposeDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanPurposeRepositoryJPA extends JpaRepository<LoanPurposeDB, Long> {
    Optional<LoanPurposeDB> findByNameRu(String name);
    Optional<LoanPurposeDB> findByCode(String code);
    Optional<LoanPurposeDB> findByCategory(String category);
}
