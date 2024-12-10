package com.servinetcomputers.api.core.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppException {
    public NotFoundException(String detail) {
        super(detail, HttpStatus.NOT_FOUND);
    }
}
