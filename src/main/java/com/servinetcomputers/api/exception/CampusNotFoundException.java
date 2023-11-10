package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class CampusNotFoundException extends CampusIdException {
    public CampusNotFoundException(int campusId) {
        super("Campus ID #" + campusId + " is not found",
                HttpStatus.NOT_FOUND,
                "CAMPUS NOT FOUND",
                campusId);
    }
}
