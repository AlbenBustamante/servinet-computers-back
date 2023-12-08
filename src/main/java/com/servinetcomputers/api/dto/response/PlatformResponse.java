package com.servinetcomputers.api.dto.response;

import lombok.Getter;

/**
 * The platform dto model for responses.
 */
@Getter
public class PlatformResponse extends ModelResponse {
    private final String name;

    public PlatformResponse(int id, String name, boolean isAvailable, String createdAt, String updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.name = name;
    }
}
