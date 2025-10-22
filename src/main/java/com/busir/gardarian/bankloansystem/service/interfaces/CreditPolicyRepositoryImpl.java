package com.busir.gardarian.bankloansystem.service.interfaces;

import com.busir.gardarian.bankloansystem.entity.CreditPolicies;

import java.util.List;

public interface CreditPolicyRepositoryImpl {
    CreditPolicies getCreditActualPolicies();
    CreditPolicies getById(Long id);
    List<CreditPolicies> getAll();
    CreditPolicies save(CreditPolicies creditPolicies);
    Boolean delete(Long id);
}
