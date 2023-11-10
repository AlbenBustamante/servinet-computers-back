package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AlreadyExistsException extends AppException {
    private final String property;

    protected AlreadyExistsException(String detail, HttpStatus status, String title, String property) {
        super(detail, status, title);
        this.property = property;
    }
}
