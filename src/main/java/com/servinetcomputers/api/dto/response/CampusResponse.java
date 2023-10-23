package com.servinetcomputers.api.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The campus dto model for responses.
 */
@Getter
public class CampusResponse extends ModelResponse {
    private final String address, cellphone;
    private final List<PlatformResponse> platforms;

    public CampusResponse(int id, String address, String cellphone, List<PlatformResponse> platforms, boolean isAvailable,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.address = address;
        this.cellphone = cellphone;
        this.platforms = platforms;
    }
}
