package com.servinetcomputers.api.domain.platform.domain.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The platform dto model for responses.
 */
@Getter
public class PlatformResponse extends ModelResponse {
    private final String name;

    public PlatformResponse(int id, String name, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate,
                            String createdBy, String modifiedBy) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.name = name;
    }
}
