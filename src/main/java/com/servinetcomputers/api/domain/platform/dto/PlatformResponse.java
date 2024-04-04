package com.servinetcomputers.api.domain.platform.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The platform dto model for responses.
 */
@Getter
public class PlatformResponse extends ModelResponse {
    private final String name;

    public PlatformResponse(int id, String name, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        super(id, enabled, createdDate, modifiedDate);
        this.name = name;
    }
}
