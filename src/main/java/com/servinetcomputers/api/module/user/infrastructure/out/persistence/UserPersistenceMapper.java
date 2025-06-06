package com.servinetcomputers.api.module.user.infrastructure.out.persistence;

import com.servinetcomputers.api.core.audit.infra.AuditablePersistenceMapper;
import com.servinetcomputers.api.module.user.domain.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapeador de dominio a entidad y viceversa para el modelo de usuarios.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserPersistenceMapper extends AuditablePersistenceMapper {
    @Mapping(target = "audit", expression = "java(mapAuditToDomain(entity))")
    public abstract User toDomain(UserEntity entity);

    public abstract List<User> toDomains(List<UserEntity> entities);

    public abstract UserEntity toEntity(User domain);

    @AfterMapping
    protected void mapAuditFields(@MappingTarget UserEntity entity, User domain) {
        mapAbstractAuditFields(entity, domain.audit());
    }
}
