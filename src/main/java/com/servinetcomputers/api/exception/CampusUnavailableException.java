package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class CampusUnavailableException extends CampusIdException {
    public CampusUnavailableException(int campusId) {
        super("The campus ID #" + campusId + " is unavailable",
                HttpStatus.BAD_REQUEST,
                "CAMPUS UNAVAILABLE",
                campusId);
    }
}
