package com.servinetcomputers.api.dto.response;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * The balance's dto model as response.
 */
@Getter
public class BalanceResponse extends ModelResponse {
    private final String platformName;
    private final int campusId;
    private final BigDecimal initialBalance, finalBalance;

    public BalanceResponse(int id, boolean isAvailable, String createdAt, String updatedAt, String platformName,
                           int campusId, BigDecimal initialBalance, BigDecimal finalBalance) {
        super(id, isAvailable, createdAt, updatedAt);
        this.platformName = platformName;
        this.campusId = campusId;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }
}
