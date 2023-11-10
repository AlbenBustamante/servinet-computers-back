package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class PlatformUnavailableException extends PlatformIdException {
    public PlatformUnavailableException(int platformId) {
        super("The platform ID #" + platformId + " is unavailable",
                HttpStatus.BAD_REQUEST,
                "PLATFORM UNAVAILABLE",
                platformId);
    }
}
