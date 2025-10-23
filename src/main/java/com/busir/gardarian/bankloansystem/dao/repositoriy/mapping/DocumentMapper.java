package com.busir.gardarian.bankloansystem.dao.repositoriy.mapping;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.DocumentDB;
import com.busir.gardarian.bankloansystem.dao.repositoriy.models.LoanApplicationDB;
import com.busir.gardarian.bankloansystem.dao.repositoriy.models.UserDB;
import com.busir.gardarian.bankloansystem.entity.Document;
import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DocumentMapper {
    private final EntityManager em;

    public List<Document> toEntity(List<DocumentDB> documentDBList) {
        return documentDBList.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<DocumentDB> toDB(List<Document> documentList) {
        return documentList.stream()
                .map(this::toDB)
                .toList();
    }

    public Document toEntity(DocumentDB documentDB) {
        if(documentDB == null)
            return null;

        Document document = new Document();

        if (documentDB.getId() != null)
            document.setId(Long.valueOf(documentDB.getId()));
        document.setApplicationId(Long.valueOf(documentDB.getApplication().getId()));
        document.setDocumentType(documentDB.getDocumentType());
        document.setFilePath(documentDB.getFilePath());
        document.setOriginalName(documentDB.getOriginalName());
        document.setUploadedAt(Timestamp.valueOf(documentDB.getUploadedAt()));
        document.setVerificationStatus(documentDB.getVerificationStatus());
        document.setVerificationsNotes(documentDB.getVerificationNotes());

        return document;
    }

    public DocumentDB toDB(Document document) {
        if(document == null)
            return null;

        DocumentDB documentDB = new DocumentDB();

        if (document.getId() != null)
            documentDB.setId(Math.toIntExact(document.getId()));

        LoanApplicationDB loanApplicationDB = em.getReference(LoanApplicationDB.class, document.getApplicationId());
        documentDB.setApplication(loanApplicationDB);

        documentDB.setDocumentType(document.getDocumentType());
        documentDB.setFilePath(document.getFilePath());
        documentDB.setOriginalName(document.getOriginalName());
        documentDB.setUploadedAt(document.getUploadedAt().toLocalDateTime());
        documentDB.setVerificationStatus(document.getVerificationStatus());
        documentDB.setVerificationNotes(document.getVerificationsNotes());

        return documentDB;
    }
}
