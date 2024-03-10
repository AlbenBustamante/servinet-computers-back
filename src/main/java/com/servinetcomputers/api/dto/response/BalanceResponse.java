package com.servinetcomputers.api.dto.response;

import lombok.Getter;

/**
 * The balance's dto model as response.
 */
@Getter
public class BalanceResponse extends ModelResponse {
    private final String platformName, initialBalance, finalBalance;
    private final int campusId;

    public BalanceResponse(int id, boolean isAvailable, String createdAt, String updatedAt, String platformName,
                           int campusId, String initialBalance, String finalBalance) {
        super(id, isAvailable, createdAt, updatedAt);
        this.platformName = platformName;
        this.campusId = campusId;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }
}
