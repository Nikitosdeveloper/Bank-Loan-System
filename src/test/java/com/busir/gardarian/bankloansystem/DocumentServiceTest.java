package com.busir.gardarian.bankloansystem;

import com.busir.gardarian.bankloansystem.entity.Document;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentVerificationStatus;
import com.busir.gardarian.bankloansystem.service.DocumentService;
import com.busir.gardarian.bankloansystem.service.dto.DocumentResult;
import com.busir.gardarian.bankloansystem.service.exception.DocumentsNameException;
import com.busir.gardarian.bankloansystem.service.interfaces.DocumentRepositoryImp;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DocumentServiceTest {
    @MockitoBean
    private DocumentRepositoryImp repository;
    @Autowired
    private DocumentService documentService;

    @Test
    @Order(1)
    public void submitDocumentTest() throws DocumentsNameException {
        Long applicationId = 1L;
        DocumentType type = DocumentType.PASSPORT;

        MultipartFile file = readTestPassport();

        when(repository.save(any(Document.class))).thenReturn(new Document());

        documentService.storeDocument(file, applicationId, type);

    }

    @Test
    @Order(2)
    public void getDocumentsTest()  {
        Long applicationId = 1L;

        when(repository.findByLoanApplicationId(applicationId)).thenReturn(
                List.of(new Document(
                        3L,
                        2L,
                        DocumentType.PASSPORT,
                        "f7aaa077-aca8-4465-bd12-1d764e510dc6.jpg",
                        "passport2.pdf",
                        Timestamp.from(Instant.now()),
                        DocumentVerificationStatus.VERIFIED,
                        ""
                ))
        );

        List<DocumentResult> documents = documentService.getDocumentsForLoanApplication(1L);

        System.out.println(documents);
    }

    private MultipartFile readTestPassport(){

        String path = "static/testPassport.jpg";

        ClassPathResource resource = new ClassPathResource(path);

        try(InputStream inputStream = resource.getInputStream()) {

            byte[] content = inputStream.readAllBytes();



            String filename = path.substring(path.lastIndexOf("/") + 1);;

            return new ByteArrayMultipartFile(
                    content,
                    filename,
                    filename,
                    getContentType(filename)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getContentType(String filename) {
        if (filename.toLowerCase().endsWith(".pdf")) {
            return "application/pdf";
        } else if (filename.toLowerCase().endsWith(".png")) {
            return "image/png";
        } else if (filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".jpeg")) {
            return "image/jpeg";
        }
        return "application/octet-stream";
    }

    static class ByteArrayMultipartFile implements MultipartFile {
        private final byte[] content;
        private final String name;
        private final String originalFilename;
        private final String contentType;

        public ByteArrayMultipartFile(byte[] content, String name,
                                      String originalFilename, String contentType) {
            this.content = content;
            this.name = name;
            this.originalFilename = originalFilename;
            this.contentType = contentType;
        }

        @Override
        public String getName() { return name; }

        @Override
        public String getOriginalFilename() { return originalFilename; }

        @Override
        public String getContentType() { return contentType; }

        @Override
        public boolean isEmpty() { return content == null || content.length == 0; }

        @Override
        public long getSize() { return content.length; }

        @Override
        public byte[] getBytes() throws IOException { return content; }

        @Override
        public InputStream getInputStream() throws IOException {
            return new java.io.ByteArrayInputStream(content);
        }

        @Override
        public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
            java.nio.file.Files.write(dest.toPath(), content);
        }
    }
}
