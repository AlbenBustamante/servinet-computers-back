package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class PasswordsDoNotMatchException extends AppException {
    public PasswordsDoNotMatchException() {
        super("The passwords do not match",
                HttpStatus.BAD_REQUEST,
                "THE PASSWORDS DO NOT MATCH");
    }
}
