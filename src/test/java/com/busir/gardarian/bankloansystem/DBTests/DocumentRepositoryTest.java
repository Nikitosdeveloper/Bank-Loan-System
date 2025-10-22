package com.busir.gardarian.bankloansystem.DBTests;

import com.busir.gardarian.bankloansystem.DocumentServiceTest;
import com.busir.gardarian.bankloansystem.entity.Document;
import com.busir.gardarian.bankloansystem.entity.enums.DocumentType;
import com.busir.gardarian.bankloansystem.service.DocumentService;
import com.busir.gardarian.bankloansystem.service.exception.DocumentsNameException;
import com.busir.gardarian.bankloansystem.service.interfaces.DocumentRepositoryImp;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanApplicationRepositoryImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class DocumentRepositoryTest {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentRepositoryImp documentRepository;
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private LoanApplicationRepositoryImpl loanApplicationRepository;

    @Test
    @Order(1)
    public void storeDocumentTest() throws DocumentsNameException {
        Long userId = userRepository.findByEmail("yakovnikita01@gmail.com").getId();

        Long applicationId = loanApplicationRepository.getByUserId(userId).get(0).getId();
        DocumentType type = DocumentType.PASSPORT;

        MultipartFile file = readTestPassport();


        documentService.storeDocument(file, applicationId, type);
    }

    private MultipartFile readTestPassport(){

        String path = "static/testPassport.jpg";

        ClassPathResource resource = new ClassPathResource(path);

        try(InputStream inputStream = resource.getInputStream()) {

            byte[] content = inputStream.readAllBytes();



            String filename = path.substring(path.lastIndexOf("/") + 1);;

            return new DocumentRepositoryTest.ByteArrayMultipartFile(
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
