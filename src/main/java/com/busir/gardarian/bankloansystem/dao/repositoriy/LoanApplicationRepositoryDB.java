package com.busir.gardarian.bankloansystem.dao.repositoriy;

import com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces.LoanApplicationRepositoryJPA;
import com.busir.gardarian.bankloansystem.dao.repositoriy.mapping.LoanApplicationMapper;
import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanApplicationRepositoryImpl;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public List<LoanApplication> getAll() {
        List<LoanApplication> list = mapper.toEntity(repository.findAll());
        return list;
    }

    @Transactional
    @Override
    public LoanApplication getById(Long id) {
        LoanApplication obj = mapper.toEntity(repository.findById(id).orElse(null));
        return obj;
    }

    @Transactional
    @Override
    public List<LoanApplication> getByManagerId(Long id) {
        List<LoanApplication> list = mapper.toEntity(repository.findByManagerId(Math.toIntExact(id)));
        return list;
    }

    @Transactional
    @Override
    public List<LoanApplication> getFreeLoanApplications() {
        List<LoanApplication> list = mapper.toEntity(repository.findByManagerIsNull());
        return list;
    }

    @Transactional
    @Override
    public List<LoanApplication> getByUserId(Long userId) {
        List<LoanApplication> list = mapper.toEntity(repository.findByUserId(userId));
        return list;
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
