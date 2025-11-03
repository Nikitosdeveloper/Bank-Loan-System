package com.busir.gardarian.bankloansystem.service;

import com.busir.gardarian.bankloansystem.entity.Document;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import com.busir.gardarian.bankloansystem.service.dto.DocumentDecision;
import com.busir.gardarian.bankloansystem.service.dto.DocumentResult;
import com.busir.gardarian.bankloansystem.service.exception.*;
import com.busir.gardarian.bankloansystem.service.interfaces.DocumentRepositoryImp;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanApplicationRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final Path fileStorageLocation;
    private final List<String> allowedFormats;
    private final LoanApplicationRepositoryImpl loanApplicationRepository;
    private final DocumentRepositoryImp documentRepository;

    public DocumentService(@Value("${file.upload-dir}") String uploadDirectory,
                           @Value("${file.allowed-formats}") String allowedFormats,
                           LoanApplicationRepositoryImpl loanApplicationRepository,
                           DocumentRepositoryImp documentRepository) {
        this.fileStorageLocation = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        this.allowedFormats = Arrays.stream(allowedFormats.split(","))
                .map(extension -> "." + extension)
                .collect(Collectors.toList());
        this.loanApplicationRepository = loanApplicationRepository;
        this.documentRepository = documentRepository;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Boolean storeDocument(MultipartFile document, Long applicationId, DocumentType type) throws DocumentsNameException {
        if (document.isEmpty()) {
            throw new DocumentIsEmptyException("document is empty");
        }
        if (applicationId == null || loanApplicationRepository.getById(applicationId) == null) {
            throw new IncorrectLoanApplicationIdException("Incorrect application id");
        }

        String originalFilename = document.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        String fileName = UUID.randomUUID().toString() + fileExtension;

        try {
            if (fileName.contains("..")){
                throw new DocumentsNameException("Invalid document's name");
            }
            if (!allowedFormats.contains(fileExtension)){
                throw new DocumentsNameException("Invalid document's extension");
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(document.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            Document documentInBD = new Document(
                    null,
                    applicationId,
                    type,
                    fileName,
                    originalFilename,
                    Timestamp.from(Instant.now()),
                    DocumentVerificationStatus.PENDING,
                    ""
            );

            documentRepository.save(documentInBD);

            return true;
        } catch (DocumentsNameException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DocumentResult> getDocumentsForLoanApplication(Long applicationId) {
        List<Document> documents = documentRepository.findByLoanApplicationId(applicationId);

        return documents.stream()
                .map(DocumentResult::new)
                .toList();
    }

    public Resource getDocumentFileById(Long id) {
        Document document = documentRepository.findById(id);
        if (document == null) {
            throw new DocumentNotFoundException("Document not found");
        }

        Path filePath = Paths.get("documents").resolve(document.getFilePath());
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return resource;
    }

    public void documentVerification(DocumentDecision decision){
        Document document = documentRepository.findById(decision.getDocumentId());

        document.setVerificationStatus(decision.getVerificationStatus());
        document.setVerificationsNotes(decision.getVerificationNotes());

        documentRepository.save(document);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return "";
        }
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex);
    }
}



