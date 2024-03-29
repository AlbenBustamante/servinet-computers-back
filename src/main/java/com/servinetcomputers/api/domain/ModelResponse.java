package com.servinetcomputers.api.domain;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Abstract class for the dto responses.
 */
@Getter
public abstract class ModelResponse {
    private final int id;
    private final boolean isAvailable;
    private final LocalDateTime createdAt, updatedAt;

    public ModelResponse(int id, boolean isAvailable, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
