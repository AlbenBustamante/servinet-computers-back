package com.servinetcomputers.api.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String uploadFile(String folder, MultipartFile multipartFile);

    String[] uploadFiles(String folder, MultipartFile[] multipartFiles);
}
