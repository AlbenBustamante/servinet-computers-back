package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PlatformNameNotFoundException extends AppException {
    private final String platformName;

    public PlatformNameNotFoundException(String platformName) {
        super("Platform name: " + platformName + " is not found",
                HttpStatus.NOT_FOUND,
                "PLATFORM NOT FOUND");
        this.platformName = platformName;
    }
}
