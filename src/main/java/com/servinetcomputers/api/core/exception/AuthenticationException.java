package com.servinetcomputers.api.core.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends AppException {
    public AuthenticationException(String detail) {
        super(detail, HttpStatus.UNAUTHORIZED);
    }
}
