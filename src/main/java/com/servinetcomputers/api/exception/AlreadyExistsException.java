package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends AppException {
    public AlreadyExistsException(String detail) {
        super(detail, HttpStatus.CONFLICT);
    }
}
