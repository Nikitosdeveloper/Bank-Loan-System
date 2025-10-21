package com.busir.gardarian.bankloansystem.service.stub;

import com.busir.gardarian.bankloansystem.entity.Document;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import com.busir.gardarian.bankloansystem.service.interfaces.DocumentRepositoryImp;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Profile("devService")
public class StubDocumentRepository implements DocumentRepositoryImp {
    @Override
    public List<Document> findAll() {
        return List.of(
                new Document(
                1L,
                1L,
                DocumentType.PASSPORT,
                "TESTPATH",
                "pasport.pdf",
                Timestamp.from(Instant.now()),
                DocumentVerificationStatus.PENDING,
                ""
                ),
                new Document(
                        2L,
                        1L,
                        DocumentType.INCOME_CERTIFICATE,
                        "TESTPATH2",
                        "income.pdf",
                        Timestamp.from(Instant.now()),
                        DocumentVerificationStatus.PENDING,
                        ""
                ),
                new Document(
                        3L,
                        2L,
                        DocumentType.PASSPORT,
                        "TESTPATH3",
                        "passport2.pdf",
                        Timestamp.from(Instant.now()),
                        DocumentVerificationStatus.VERIFIED,
                        ""
                )
        );
    }

    @Override
    public List<Document> findByLoanApplicationId(Long id) {
        Document document = new Document(
                3L,
                2L,
                DocumentType.PASSPORT,
                "f7aaa077-aca8-4465-bd12-1d764e510dc6.jpg",
                "passport2.pdf",
                Timestamp.from(Instant.now()),
                DocumentVerificationStatus.VERIFIED,
                ""
        );
        return List.of(document);
    }

    @Override
    public List<Document> findByDocumentType(DocumentType type) {
        return List.of(
                new Document(
                        1L,
                        1L,
                        DocumentType.PASSPORT,
                        "TESTPATH",
                        "pasport.pdf",
                        Timestamp.from(Instant.now()),
                        DocumentVerificationStatus.PENDING,
                        ""
                ),
                new Document(
                        3L,
                        2L,
                        DocumentType.PASSPORT,
                        "TESTPATH3",
                        "passport2.pdf",
                        Timestamp.from(Instant.now()),
                        DocumentVerificationStatus.VERIFIED,
                        ""
                ));

    }

    @Override
    public List<Document> findByDocumentVerificationStatus(DocumentVerificationStatus verificationStatus) {
        return List.of(
                new Document(
                        1L,
                        1L,
                        DocumentType.PASSPORT,
                        "TESTPATH",
                        "pasport.pdf",
                        Timestamp.from(Instant.now()),
                        DocumentVerificationStatus.PENDING,
                        ""
                ),
                new Document(
                        2L,
                        1L,
                        DocumentType.INCOME_CERTIFICATE,
                        "TESTPATH2",
                        "income.pdf",
                        Timestamp.from(Instant.now()),
                        DocumentVerificationStatus.PENDING,
                        ""
                ));
    }

    @Override
    public Document findById(Long id) {
        return new Document(
                1L,
                1L,
                DocumentType.PASSPORT,
                "TESTPATH",
                "pasport.pdf",
                Timestamp.from(Instant.now()),
                DocumentVerificationStatus.PENDING,
                ""
        );
    }

    @Override
    public Document save(Document document) {
        document.setId(2L);
        return document;
    }

    @Override
    public Boolean deleteById(Long id) {
        return true;
    }
}
