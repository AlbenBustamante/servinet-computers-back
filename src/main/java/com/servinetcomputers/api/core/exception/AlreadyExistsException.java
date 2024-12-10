package com.servinetcomputers.api.core.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends AppException {
    public AlreadyExistsException(String detail) {
        super(detail, HttpStatus.CONFLICT);
    }
}
