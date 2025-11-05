package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.DocumentDecisionRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.DocumentResponce;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.service.DocumentService;
import com.busir.gardarian.bankloansystem.service.dto.DocumentDecision;
import com.busir.gardarian.bankloansystem.service.dto.DocumentResult;
import com.busir.gardarian.bankloansystem.service.dto.DownloadFileResult;
import com.busir.gardarian.bankloansystem.service.exception.DocumentsNameException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("client/document/submit")
    public ResponseEntity<String> submitDocuments(
            @RequestParam MultipartFile file,
            @RequestParam Long applicationId,
            @RequestParam String type
            ) throws DocumentsNameException {

        DocumentType documentType = DocumentType.valueOf(type.toUpperCase());

        Boolean isStore = documentService.storeDocument(file, applicationId, documentType);

        if(isStore){
            return ResponseEntity.ok("Document successful store");
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("client/document/loan-application/{id}")
    public ResponseEntity<List<DocumentResponce>> getDocuments(@PathVariable Long id){
        List<DocumentResult> list = documentService.getDocumentsForLoanApplication(id);
        List<DocumentResponce> responses = list.stream()
                .map(DocumentResponce::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("client/document/download/{id}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long id){
        DownloadFileResult file = documentService.getDocumentFileById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getOriginalFileName() + "\"")
                .body(file.getFile());
    }

    @GetMapping("client/document/view/{id}")
    public ResponseEntity<Resource> viewDocument(@PathVariable Long id) {
        DownloadFileResult result = documentService.getDocumentFileById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .contentType(MediaType.parseMediaType(result.getContentType()))
                .body(result.getFile());
    }

    @PostMapping("manager/document/verification")
    public ResponseEntity<String> verifyDocuments(@Valid @RequestBody DocumentDecisionRequest request){

        documentService.documentVerification(request.createDocumentDecision());

        return ResponseEntity.ok("Success");
    }
}
