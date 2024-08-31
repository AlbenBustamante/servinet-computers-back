package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The main throwable exception.
 */
@Getter
public class AppException extends RuntimeException {
    private final HttpStatus status;

    public AppException(String detail, HttpStatus status) {
        super(detail + ".");
        this.status = status;
    }
}
