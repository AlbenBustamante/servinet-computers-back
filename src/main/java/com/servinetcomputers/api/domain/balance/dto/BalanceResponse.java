package com.servinetcomputers.api.domain.balance.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The balance's dto model as response.
 */
@Getter
public class BalanceResponse extends ModelResponse {
    private final String platformName, initialBalance, finalBalance;
    private final int campusId;

    public BalanceResponse(int id, boolean isAvailable, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy,
                           String modifiedBy, String platformName, int campusId, String initialBalance, String finalBalance) {
        super(id, isAvailable, createdAt, updatedAt, createdBy, modifiedBy);
        this.platformName = platformName;
        this.campusId = campusId;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }
}
