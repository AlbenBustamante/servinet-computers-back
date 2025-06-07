package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.core.audit.infra.AuditableDto;
import com.servinetcomputers.api.module.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SafeBaseDto extends AuditableDto<Integer> {
    private int safeDetailId, base;
    private BaseDto detailBase;
}
