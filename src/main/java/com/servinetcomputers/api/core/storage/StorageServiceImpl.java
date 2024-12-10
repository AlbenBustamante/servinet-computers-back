package com.servinetcomputers.api.core.storage;

import com.google.cloud.storage.Storage;
import com.servinetcomputers.api.core.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StorageServiceImpl implements StorageService {

    private final Storage storage;

    @Value("${bucket-name}")
    private String bucketName;

    @Override
    public String uploadFile(String folder, MultipartFile file) {
        try {
            final var uniqueFilename = folder.isBlank() ? "" : folder + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            final var bucket = storage.get(bucketName);
            final var blob = bucket.create(uniqueFilename, file.getBytes(), file.getContentType());

            return blob.getMediaLink();
        } catch (IOException e) {
            throw new AppException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String[] uploadFiles(String folder, MultipartFile[] multipartFiles) {
        final String[] files = new String[multipartFiles.length];

        for (int i = 0; i < multipartFiles.length; i++) {
            files[i] = uploadFile(folder, multipartFiles[i]);
        }

        return files;
    }

}
