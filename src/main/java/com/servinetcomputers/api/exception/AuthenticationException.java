package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

/**
 * Authentication exception.
 */
public class AuthenticationException extends AppException {
    public AuthenticationException(String detail, String title) {
        super(detail, HttpStatus.UNAUTHORIZED, title);
    }
}
