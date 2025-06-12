package com.servinetcomputers.api.core.audit.infra;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

/**
 * Mapeador base de dominio a entidad y actualizador de entidad para los modelos de auditoría.
 */
@Mapper(componentModel = "spring")
public abstract class AuditablePersistenceMapper {
    protected static final String TO_DOMAIN = "mapAuditToDomain";

    @Named(TO_DOMAIN)
    public abstract Auditable mapAuditToDomain(AuditableEntity entity);

    protected void mapAbstractAuditFields(@MappingTarget AuditableEntity entity, Auditable domain) {
        if (domain.createdDate() != null) {
            entity.setCreatedDate(domain.createdDate());
        }

        if (domain.createdBy() != null) {
            entity.setCreatedBy(domain.createdBy());
        }

        if (domain.modifiedBy() != null) {
            entity.setModifiedBy(domain.modifiedBy());
        }

        if (domain.modifiedDate() != null) {
            entity.setModifiedDate(domain.modifiedDate());
        }

        if (domain.enabled() != null) {
            entity.setEnabled(domain.enabled());
        }
    }
}
