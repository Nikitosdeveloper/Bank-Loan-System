package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import com.busir.gardarian.bankloansystem.service.dto.DocumentDecision;
import jakarta.validation.constraints.NotNull;

public record DocumentDecisionRequest(
     @NotNull
     Long documentId,
     @NotNull
     DocumentVerificationStatus verificationStatus,
     String verificationNotes
) {
    public DocumentDecision createDocumentDecision(){
        return new DocumentDecision(documentId, verificationStatus, verificationNotes);
    }
}
