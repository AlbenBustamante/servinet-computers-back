package com.servinetcomputers.api.domain.platform.domain.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The balance's dto model as response.
 */
@Getter
public class PlatformBalanceResponse extends ModelResponse {
    private final String platformName;
    private final int platformId, initialBalance, finalBalance;

    public PlatformBalanceResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy,
                                   String modifiedBy, String platformName, int platformId, int initialBalance, int finalBalance) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.platformName = platformName;
        this.platformId = platformId;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }
}
