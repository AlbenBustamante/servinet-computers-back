package com.servinetcomputers.api.module.platform.domain.dto;

import com.servinetcomputers.api.module.ModelResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The balance's dto model as response.
 */
@Setter
@Getter
public class PlatformBalanceResponse extends ModelResponse {
    private final String platformName;
    private final int platformId;
    private Integer initialBalance, finalBalance;

    public PlatformBalanceResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy,
                                   String modifiedBy, String platformName, int platformId, Integer initialBalance, Integer finalBalance) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.platformName = platformName;
        this.platformId = platformId;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }
}
