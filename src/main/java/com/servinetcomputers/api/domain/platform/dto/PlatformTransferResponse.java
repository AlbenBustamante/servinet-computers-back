package com.servinetcomputers.api.domain.platform.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The transfer dto model for responses.
 */
@Getter
public class PlatformTransferResponse extends ModelResponse {
    private final int platformId, value;

    public PlatformTransferResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate,
                                    String createdBy, String modifiedBy, int platformId, int value) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.platformId = platformId;
        this.value = value;
    }
}
