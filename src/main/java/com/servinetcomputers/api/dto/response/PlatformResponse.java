package com.servinetcomputers.api.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The platform dto model for responses.
 */
@Getter
public class PlatformResponse extends ModelResponse {
    private final String name;

    public PlatformResponse(int id, String name, boolean isAvailable, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.name = name;
    }
}
