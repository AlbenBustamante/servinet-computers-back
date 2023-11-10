package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class CampusAlreadyExistsException extends AlreadyExistsException {
    public CampusAlreadyExistsException(String property) {
        super("The campus with the following property: " + property + ", already exists",
                HttpStatus.BAD_REQUEST,
                "CAMPUS ALREADY EXISTS",
                property);
    }
}
