package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Generic exception for errors with the {@code userId} property.
 */
@Getter
public abstract class UserIdException extends AppException {
    private final int userId;

    protected UserIdException(String detail, HttpStatus status, String title, int userId) {
        super(detail, status, title);
        this.userId = userId;
    }
}
