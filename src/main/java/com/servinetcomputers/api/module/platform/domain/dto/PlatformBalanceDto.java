package com.servinetcomputers.api.module.platform.domain.dto;

import com.servinetcomputers.api.core.audit.infra.AuditableDto;
import lombok.Getter;
import lombok.Setter;

/**
 * The balance's dto model as response.
 */
@Setter
@Getter
public class PlatformBalanceDto extends AuditableDto<Integer> {
    private PlatformDto platform;
    private Integer initialBalance, finalBalance;
}
