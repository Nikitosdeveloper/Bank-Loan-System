package com.busir.gardarian.bankloansystem.service.dto;

import com.busir.gardarian.bankloansystem.entity.Document;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResult {
    private Long id;
    private DocumentType documentType;
    private String originalName;
    private DocumentVerificationStatus verificationStatus;
    private String verificationsNotes;

    public DocumentResult(Document document) {
        this.id = document.getId();
        this.documentType = document.getDocumentType();
        this.originalName = document.getOriginalName();
        this.verificationStatus = document.getVerificationStatus();
        this.verificationsNotes = document.getVerificationsNotes();
    }
}
