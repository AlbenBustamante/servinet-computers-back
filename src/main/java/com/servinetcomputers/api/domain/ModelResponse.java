package com.servinetcomputers.api.domain;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Abstract class for the dto responses.
 */
@Getter
public abstract class ModelResponse {
    private final int id;
    private final boolean enabled;
    private final LocalDateTime createdDate, modifiedDate;

    public ModelResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.enabled = enabled;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
