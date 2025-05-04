package com.servinetcomputers.api.module.tempcode.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempCodeDto extends AuditableDto<Integer> {
    private Integer code;
    private UserDto usedBy;
}
