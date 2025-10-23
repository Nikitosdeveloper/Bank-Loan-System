package com.busir.gardarian.bankloansystem.dao.repositoriy;

import com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces.CreditPolicyRepositoryJPA;
import com.busir.gardarian.bankloansystem.dao.repositoriy.mapping.CreditPolicyMapper;
import com.busir.gardarian.bankloansystem.entity.CreditPolicies;
import com.busir.gardarian.bankloansystem.service.interfaces.CreditPolicyRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("devDAO")
@Primary
public class CreditPolicyRepositoryDB implements CreditPolicyRepositoryImpl {
    private final CreditPolicyRepositoryJPA repository;
    private final CreditPolicyMapper mapper;

    @Override
    public CreditPolicies getCreditActualPolicies() {
        return mapper.toEntity(repository.findByIsActive(true));
    }

    @Override
    public CreditPolicies getById(Long id) {
        return mapper.toEntity(repository.findById(id).orElse(null));
    }

    @Override
    public List<CreditPolicies> getAll() {
        return mapper.toEntity(repository.findAll());
    }

    @Override
    public CreditPolicies save(CreditPolicies creditPolicies) {
        return mapper.toEntity(repository.save(mapper.toDB(creditPolicies)));
    }

    @Override
    public Boolean delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
