package com.busir.gardarian.bankloansystem.dao.repositoriy;

import com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces.LoanApplicationRepositoryJPA;
import com.busir.gardarian.bankloansystem.dao.repositoriy.mapping.LoanApplicationMapper;
import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanApplicationRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("devDAO")
@Primary
public class LoanApplicationRepositoryDB implements LoanApplicationRepositoryImpl {
    private final LoanApplicationRepositoryJPA repository;
    private final LoanApplicationMapper mapper;

    @Override
    public List<LoanApplication> getAll() {
        return mapper.toEntity(repository.findAll());
    }

    @Override
    public LoanApplication getById(Long id) {
        return mapper.toEntity(repository.findById(id).orElse(null));
    }

    @Override
    public List<LoanApplication> getByManagerId(Long id) {
        return mapper.toEntity(repository.findByManagerId(Math.toIntExact(id)));
    }

    @Override
    public List<LoanApplication> getFreeLoanApplications() {
        return mapper.toEntity(repository.findByManagerIsNull());
    }

    @Override
    public List<LoanApplication> getByUserId(Long userId) {
        return mapper.toEntity(repository.findByUserId(userId));
    }

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        return mapper.toEntity(repository.save(mapper.toDB(loanApplication)));
    }

    @Override
    public Boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
