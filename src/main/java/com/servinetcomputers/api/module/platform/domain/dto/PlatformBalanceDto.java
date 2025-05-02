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
public class PlatformBalanceDto extends ModelResponse {
    private PlatformResponse platform;
    private Integer initialBalance, finalBalance;

    public PlatformBalanceDto(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy,
                              String modifiedBy, PlatformResponse platform, Integer initialBalance, Integer finalBalance) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.platform = platform;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }
}
