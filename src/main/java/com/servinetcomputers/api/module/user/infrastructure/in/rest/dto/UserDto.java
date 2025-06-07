package com.servinetcomputers.api.module.user.infrastructure.in.rest.dto;

import com.servinetcomputers.api.core.audit.infra.AuditableDto;
import com.servinetcomputers.api.core.util.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Modelo DTO para usuarios.
 */
@Getter
@RequiredArgsConstructor
public class UserDto extends AuditableDto {
    private final int id;
    private final String name, lastName, email, code;
    private final Role role;
}
