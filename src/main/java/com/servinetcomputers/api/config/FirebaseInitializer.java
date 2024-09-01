package com.servinetcomputers.api.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.servinetcomputers.api.exception.AppException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {

    @Value("${bucket-name}")
    private String bucketName;

    @PostConstruct
    private void initialize() throws IOException {
        final var serviceAccountKey = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");

        if (serviceAccountKey == null) {
            throw new AppException("serviceAccountKey is null", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        final var options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountKey))
                .setStorageBucket(bucketName)
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    @Bean
    public Storage storage() throws IOException {
        final var serviceAccountKey = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");

        if (serviceAccountKey == null) {
            throw new AppException("serviceAccountKey is null", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountKey))
                .build()
                .getService();
    }

}
