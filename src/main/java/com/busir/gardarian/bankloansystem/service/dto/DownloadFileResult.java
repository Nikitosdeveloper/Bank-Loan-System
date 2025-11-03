package com.busir.gardarian.bankloansystem.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileResult {
    private Resource file;
    private String originalFileName;
    private String contentType;
}
