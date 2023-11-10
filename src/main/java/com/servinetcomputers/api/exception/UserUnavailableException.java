package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class UserUnavailableException extends UserIdException {
    public UserUnavailableException(int userId) {
        super("The user ID #" + userId + " is unavailable",
                HttpStatus.BAD_REQUEST,
                "USER UNAVAILABLE",
                userId);
    }
}
