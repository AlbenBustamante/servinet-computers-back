package com.servinetcomputers.api.module.platform.domain.dto;

import com.servinetcomputers.api.core.audit.infra.AuditableDto;
import lombok.Getter;
import lombok.Setter;

/**
 * The platform dto model for responses.
 */
@Setter
@Getter
public class PlatformDto extends AuditableDto<Integer> {
    private String name;
}
