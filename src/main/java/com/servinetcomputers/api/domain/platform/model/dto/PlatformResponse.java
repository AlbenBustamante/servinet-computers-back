package com.servinetcomputers.api.domain.platform.model.dto;

import com.servinetcomputers.api.domain.ModelResponse;
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
