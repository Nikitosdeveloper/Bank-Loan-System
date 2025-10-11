package com.busir.gardarian.bankloansystem.entity;

import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Document {
    private Long id;
    private Long applicationId;
    private DocumentType documentType;
    private String filePath;
    private String originalName;
    private Timestamp uploadedAt;
    private DocumentVerificationStatus verificationStatus;
    private String verificationsNotes;
}
