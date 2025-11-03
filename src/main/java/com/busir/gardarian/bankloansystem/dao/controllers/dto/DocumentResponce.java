package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import com.busir.gardarian.bankloansystem.service.dto.DocumentResult;
import org.springframework.core.io.Resource;

public record DocumentResponce(
        DocumentType documentType,
        String originalName,
        DocumentVerificationStatus verificationStatus,
        String verificationsNotes
) {
    public static DocumentResponce from(final DocumentResult document) {
        return new DocumentResponce(
                document.getDocumentType(),
                document.getOriginalName(),
                document.getVerificationStatus(),
                document.getVerificationsNotes()
        );
    }
}
