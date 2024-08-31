package com.servinetcomputers.api.storage;

import com.google.api.client.util.Value;
import com.google.cloud.storage.Storage;
import com.servinetcomputers.api.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StorageServiceImpl implements StorageService {

    private final Storage storage;

    @Value("${bucket-name}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            final var bucket = storage.get(bucketName);
            final var blob = bucket.create(file.getOriginalFilename(), file.getBytes(), file.getContentType());

            return blob.getMediaLink();
        } catch (IOException e) {
            throw new AppException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<String> uploadFiles(List<MultipartFile> multipartFiles) {
        final List<String> files = new ArrayList<>();

        multipartFiles.forEach(multipartFile -> files.add(uploadFile(multipartFile)));

        return files;
    }

}
