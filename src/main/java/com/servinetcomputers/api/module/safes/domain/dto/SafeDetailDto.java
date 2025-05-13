package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.module.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SafeDetailDto extends AuditableDto<Integer> {
    private BaseDto detailInitialBase, detailFinalBase;
    private Integer safeId, calculatedBase, initialBase, finalBase;
    private SafeDto safe;
}
