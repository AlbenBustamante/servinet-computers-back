package com.servinetcomputers.api.module.passwordtempcode.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordTempCodeDto extends AuditableDto<Integer> {
    private String code;
    private Integer usedById;
}
