package com.servinetcomputers.api.dto.response;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The transfer dto model for responses.
 */
@Getter
public class TransferResponse extends ModelResponse {
    private final String platformName;
    private final BigDecimal value;

    public TransferResponse(int id, String platformName, BigDecimal value, boolean isAvailable, LocalDateTime createdAt,
                            LocalDateTime updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.platformName = platformName;
        this.value = value;
    }
}
