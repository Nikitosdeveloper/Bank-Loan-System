package com.busir.gardarian.bankloansystem.dao.repositoriy;

import com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces.DocumentRepositoryJPA;
import com.busir.gardarian.bankloansystem.dao.repositoriy.mapping.DocumentMapper;
import com.busir.gardarian.bankloansystem.entity.Document;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import com.busir.gardarian.bankloansystem.service.interfaces.DocumentRepositoryImp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("devDAO")
@Primary
public class DocumentRepositoryDB implements DocumentRepositoryImp {
    private final DocumentRepositoryJPA repository;
    private final DocumentMapper mapper;

    @Override
    public List<Document> findAll() {
        return mapper.toEntity(repository.findAll());
    }

    @Override
    public List<Document> findByLoanApplicationId(Long id) {
        return mapper.toEntity(repository.findByApplicationId(id));
    }

    @Override
    public List<Document> findByDocumentType(DocumentType type) {
        return mapper.toEntity(repository.findByDocumentType(type));
    }

    @Override
    public List<Document> findByDocumentVerificationStatus(DocumentVerificationStatus verificationStatus) {
        return mapper.toEntity(repository.findByVerificationStatus(verificationStatus));
    }

    @Override
    public Document findById(Long id) {
        return mapper.toEntity(repository.findById(id).orElse(null));
    }

    @Override
    public Document save(Document document) {
        return mapper.toEntity(repository.save(mapper.toDB(document)));
    }

    @Override
    public Boolean deleteById(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
