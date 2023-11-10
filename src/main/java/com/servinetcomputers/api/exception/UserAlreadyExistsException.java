package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AlreadyExistsException {
    public UserAlreadyExistsException(String property) {
        super("The user with the following property: " + property + ", already exists",
                HttpStatus.BAD_REQUEST,
                "User already exists",
                property);
    }
}
