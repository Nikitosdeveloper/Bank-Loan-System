package com.busir.gardarian.bankloansystem.service.dto;

import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DocumentDecision {
    private Long documentId;
    private DocumentVerificationStatus verificationStatus;
    private String verificationNotes;
}
