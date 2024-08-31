package com.servinetcomputers.api.storage;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    String uploadFile(MultipartFile multipartFile);

    List<String> uploadFiles(List<MultipartFile> multipartFiles);
}
