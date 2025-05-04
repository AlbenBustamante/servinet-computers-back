package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SafeDto extends AuditableDto<Integer> {
    private int numeral;
}
