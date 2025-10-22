package com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.DocumentDB;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepositoryJPA extends JpaRepository<DocumentDB, Long> {
    Optional<DocumentDB> findById(Long id);
    List<DocumentDB> findByApplicationId(Long id);
    List<DocumentDB> findByDocumentType(DocumentType documentType);
    List<DocumentDB> findByVerificationStatus(DocumentVerificationStatus verificationStatus);

}
