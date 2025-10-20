package com.busir.gardarian.bankloansystem.service.interfaces;

import com.busir.gardarian.bankloansystem.entity.Document;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;

import java.util.List;

public interface DocumentRepositoryImp {
    List<Document> findAll();
    List<Document> findByLoanApplicationId(Long id);
    List<Document> findByDocumentType(DocumentType type);
    List<Document> findByDocumentVerificationStatus(DocumentVerificationStatus verificationStatus);
    Document findById(int id);
    Document save(Document document);
    Boolean deleteById(int id);
}
