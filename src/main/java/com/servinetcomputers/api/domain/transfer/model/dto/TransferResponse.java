package com.servinetcomputers.api.domain.transfer.model.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The transfer dto model for responses.
 */
@Getter
public class TransferResponse extends ModelResponse {
    private final String platformName, value;

    public TransferResponse(int id, String platformName, String value, boolean isAvailable, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.platformName = platformName;
        this.value = value;
    }
}