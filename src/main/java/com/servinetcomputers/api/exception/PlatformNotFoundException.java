package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class PlatformNotFoundException extends PlatformIdException {
    public PlatformNotFoundException(int platformId) {
        super("Platform ID #" + platformId + " is not found",
                HttpStatus.NOT_FOUND,
                "PLATFORM NOT FOUND",
                platformId);
    }
}
