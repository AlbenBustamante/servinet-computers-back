package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.core.audit.infra.AuditableDto;
import com.servinetcomputers.api.module.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SafeDetailDto extends AuditableDto<Integer> {
    private BaseDto detailInitialBase, detailFinalBase;
    private Integer safeId, calculatedBase, initialBase, finalBase;
    private SafeDto safe;
    private List<SafeBaseDto> bases;
}
