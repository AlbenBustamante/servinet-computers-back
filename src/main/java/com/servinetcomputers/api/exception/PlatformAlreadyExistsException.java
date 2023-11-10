package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class PlatformAlreadyExistsException extends AlreadyExistsException {
    public PlatformAlreadyExistsException(String property) {
        super("The platform with the following property: " + property + ", already exists",
                HttpStatus.BAD_REQUEST,
                "PLATFORM ALREADY EXISTS",
                property);
    }
}
