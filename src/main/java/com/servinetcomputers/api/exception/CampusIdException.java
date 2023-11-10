package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Generic exception for errors with the {@code campusId} property.
 */
@Getter
public abstract class CampusIdException extends AppException {
    private final int campusId;

    protected CampusIdException(String detail, HttpStatus status, String title, int campusId) {
        super(detail, status, title);
        this.campusId = campusId;
    }
}
