package com.servinetcomputers.api.module.user.infrastructure.in.rest;

import com.servinetcomputers.api.core.audit.infra.AuditableDtoMapper;
import com.servinetcomputers.api.module.user.domain.User;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapeador de dominio a DTO para modelos de usuario.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserDtoMapper extends AuditableDtoMapper {
    public abstract UserDto toDto(User domain);

    @AfterMapping
    protected void mapAuditFields(@MappingTarget UserDto dto, User domain) {
        mapAbstractAuditFields(dto, domain.audit());
    }
}
