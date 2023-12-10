package com.servinetcomputers.api.dto.response;

import lombok.Getter;

/**
 * The transfer dto model for responses.
 */
@Getter
public class TransferResponse extends ModelResponse {
    private final String platformName, value, time;

    public TransferResponse(int id, String platformName, String value, boolean isAvailable, String createdAt,
                            String updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.platformName = platformName;
        this.value = value;
        this.time = createdAt.split(" ")[1];
    }
}
