package com.servinetcomputers.api.core.audit.infra;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapeador base de dominio a dto para los modelos de auditoría.
 */
@Mapper(componentModel = "spring")
public abstract class AuditableDtoMapper {
    protected void mapAbstractAuditFields(@MappingTarget AuditableDto dto, Auditable domain) {
        dto.setEnabled(domain.enabled());
        dto.setCreatedBy(domain.createdBy());
        dto.setCreatedDate(domain.createdDate());
        dto.setModifiedBy(domain.modifiedBy());
        dto.setModifiedDate(domain.modifiedDate());
    }
}
