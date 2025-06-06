package com.servinetcomputers.api.module.passwordtempcode.domain.dto;

import com.servinetcomputers.api.core.audit.infra.AuditableDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PasswordTempCodeDto extends AuditableDto<Integer> {
    private String code, userCode;
    private Integer usedById;
    private LocalDateTime expirationDate;
}
