package com.servinetcomputers.api.core.exception;

import org.springframework.http.HttpStatus;

public class InvalidTempCodeException extends AppException {
    public InvalidTempCodeException() {
        super("Código inválido", HttpStatus.UNAUTHORIZED);
    }
}
