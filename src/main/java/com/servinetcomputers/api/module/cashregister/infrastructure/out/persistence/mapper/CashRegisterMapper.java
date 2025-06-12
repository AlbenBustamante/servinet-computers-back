package com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.mapper;

import com.servinetcomputers.api.core.audit.infra.AuditablePersistenceMapper;
import com.servinetcomputers.api.module.cashregister.domain.CashRegister;
import com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.entity.CashRegisterEntity;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapeador de entidad a dominio y viceversa para las cajas registradoras.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CashRegisterMapper extends AuditablePersistenceMapper {
    @Mapping(target = "audit", qualifiedByName = TO_DOMAIN)
    public abstract CashRegister toDomain(CashRegisterEntity entity);

    public abstract List<CashRegister> toDomains(List<CashRegisterEntity> entities);

    public abstract CashRegisterEntity toEntity(CashRegister domain);

    @AfterMapping
    protected void mapAuditFields(@MappingTarget CashRegisterEntity entity, CashRegister domain) {
        mapAbstractAuditFields(entity, domain.audit());
    }
}
