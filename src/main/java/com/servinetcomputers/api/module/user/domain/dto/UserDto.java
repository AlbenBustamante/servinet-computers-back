package com.servinetcomputers.api.module.user.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.core.util.enums.Role;
import lombok.Getter;
import lombok.Setter;

/**
 * The user dto model for responses.
 */
@Getter
@Setter
public class UserDto extends AuditableDto<Integer> {
    private String name, lastName;
    private String code;
    private Role role;
}
