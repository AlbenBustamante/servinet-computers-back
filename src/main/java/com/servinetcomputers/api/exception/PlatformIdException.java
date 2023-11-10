package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class PlatformIdException extends AppException {
    private final int platformId;

    protected PlatformIdException(String detail, HttpStatus status, String title, int platformId) {
        super(detail, status, title);
        this.platformId = platformId;
    }
}
