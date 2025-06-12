package com.servinetcomputers.api.module.user.infrastructure.out.persistence;

import com.servinetcomputers.api.core.audit.infra.AuditablePersistenceMapper;
import com.servinetcomputers.api.module.user.domain.User;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapeador de dominio a entidad y viceversa para el modelo de usuarios.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserPersistenceMapper extends AuditablePersistenceMapper {
    @Mapping(target = "audit", qualifiedByName = TO_DOMAIN)
    public abstract User toDomain(UserEntity entity);

    public abstract List<User> toDomains(List<UserEntity> entities);

    public abstract UserEntity toEntity(User domain);

    @AfterMapping
    protected void mapAuditFields(@MappingTarget UserEntity entity, User domain) {
        mapAbstractAuditFields(entity, domain.audit());
    }
}
