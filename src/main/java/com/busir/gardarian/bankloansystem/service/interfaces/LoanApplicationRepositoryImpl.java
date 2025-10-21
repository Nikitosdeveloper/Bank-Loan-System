package com.busir.gardarian.bankloansystem.service.interfaces;

import com.busir.gardarian.bankloansystem.entity.LoanApplication;

import java.util.List;

public interface LoanApplicationRepositoryImpl {
    List<LoanApplication> getAll();
    LoanApplication getById(Long id);
    List<LoanApplication> getByManagerId(Long id);
    List<LoanApplication> getFreeLoanApplications();
    List<LoanApplication> getByUserId(Long userId);
    LoanApplication save(LoanApplication loanApplication);
    Boolean delete(Long id);
}
