package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserIdException {
    public UserNotFoundException(int userId) {
        super("User ID #" + userId + " is not found",
                HttpStatus.NOT_FOUND,
                "USER NOT FOUND",
                userId);
    }
}
