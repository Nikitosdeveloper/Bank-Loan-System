package com.busir.gardarian.bankloansystem.service.interfaces;

import com.busir.gardarian.bankloansystem.entity.LoanPurpose;

import java.util.List;

public interface LoanPurposeRepositoryImpl {
    public List<LoanPurpose> findAll();
    public LoanPurpose findById(Long id);
    public LoanPurpose findByName(String name);
    public LoanPurpose findByCode(String code);
    public LoanPurpose findByCategory(String category);
    public LoanPurpose save(LoanPurpose loanPurpose);
    public Boolean delete(Long id);
}
