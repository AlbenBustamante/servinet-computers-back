package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The main and abstract throwable exception.
 */
@Getter
public abstract class AppException extends RuntimeException {
    private final HttpStatus status;
    private final String title;

    protected AppException(String detail, HttpStatus status, String title) {
        super(detail + ".");
        this.status = status;
        this.title = title.toUpperCase() + ".";
    }
}
