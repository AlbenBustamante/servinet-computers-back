package com.servinetcomputers.api.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The campus dto model for responses.
 */
@Getter
public class CampusResponse extends ModelResponse {
    private final String address, cellphone;

    public CampusResponse(int id, String address, String cellphone, boolean isAvailable, LocalDateTime createdAt,
                          LocalDateTime updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.address = address;
        this.cellphone = cellphone;
    }
}
