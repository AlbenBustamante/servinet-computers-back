package com.servinetcomputers.api.domain.transfer.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The transfer dto model for responses.
 */
@Getter
public class TransferResponse extends ModelResponse {
    private final String platformName, value;

    public TransferResponse(int id, String platformName, String value, boolean isAvailable, LocalDateTime createdAt, LocalDateTime updatedAt,
                            String createdBy, String modifiedBy) {
        super(id, isAvailable, createdAt, updatedAt, createdBy, modifiedBy);
        this.platformName = platformName;
        this.value = value;
    }
}
