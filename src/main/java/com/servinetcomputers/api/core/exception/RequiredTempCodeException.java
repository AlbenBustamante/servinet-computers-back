package com.servinetcomputers.api.core.exception;

import org.springframework.http.HttpStatus;

public class RequiredTempCodeException extends AppException {
    public RequiredTempCodeException() {
        super("El código es requerido", HttpStatus.BAD_REQUEST);
    }
}
