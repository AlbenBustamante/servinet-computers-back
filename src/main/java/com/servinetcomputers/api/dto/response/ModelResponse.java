package com.servinetcomputers.api.dto.response;

import lombok.Getter;

/**
 * Abstract class for the dto responses.
 */
@Getter
public abstract class ModelResponse {
    private final int id;
    private final boolean isAvailable;
    private final String createdAt, updatedAt;

    public ModelResponse(int id, boolean isAvailable, String createdAt, String updatedAt) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
