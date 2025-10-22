package com.busir.gardarian.bankloansystem.dao.repositoriy;

import com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces.LoanPurposeRepositoryJPA;
import com.busir.gardarian.bankloansystem.dao.repositoriy.mapping.LoanPurposeMapper;
import com.busir.gardarian.bankloansystem.entity.LoanPurpose;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanPurposeRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("devDAO")
@Primary
public class LoanPurposeRepositoryDB implements LoanPurposeRepositoryImpl {
    private final LoanPurposeRepositoryJPA repository;
    private final LoanPurposeMapper mapper;

    @Override
    public List<LoanPurpose> findAll() {
        return mapper.toEntity(repository.findAll());
    }

    @Override
    public LoanPurpose findById(Long id) {
        return mapper.toEntity(repository.findById(id).orElse(null));
    }

    @Override
    public LoanPurpose findByName(String name) {
        return mapper.toEntity(repository.findByNameRu(name).orElse(null));
    }

    @Override
    public LoanPurpose findByCode(String code) {
        return mapper.toEntity(repository.findByCode(code).orElse(null));
    }

    @Override
    public LoanPurpose findByCategory(String category) {
        return mapper.toEntity(repository.findByCategory(category).orElse(null));
    }

    @Override
    public LoanPurpose save(LoanPurpose loanPurpose) {
        return mapper.toEntity(repository.save(mapper.toDB(loanPurpose)));
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
