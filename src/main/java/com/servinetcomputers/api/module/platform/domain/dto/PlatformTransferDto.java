package com.servinetcomputers.api.module.platform.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import lombok.Getter;
import lombok.Setter;

/**
 * The transfer dto model for responses.
 */
@Setter
@Getter
public class PlatformTransferDto extends AuditableDto<Integer> {
    private int value;
    private String[] voucherUrls;
    private PlatformDto platform;
}
