package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AppException {
    public BadRequestException(String detail) {
        super(detail, HttpStatus.BAD_REQUEST);
    }
}
