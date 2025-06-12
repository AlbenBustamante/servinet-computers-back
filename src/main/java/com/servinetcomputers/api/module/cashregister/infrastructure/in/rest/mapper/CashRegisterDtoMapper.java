package com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.mapper;

import com.servinetcomputers.api.core.audit.infra.AuditableDtoMapper;
import com.servinetcomputers.api.module.cashregister.domain.CashRegister;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapeador de dominio a dto para las cajas registradoras.
 */
public abstract class CashRegisterDtoMapper extends AuditableDtoMapper {
    public abstract CashRegisterDto toDto(CashRegister domain);

    public abstract List<CashRegisterDto> toDto(List<CashRegister> domains);

    @AfterMapping
    protected void mapAuditFields(@MappingTarget CashRegisterDto dto, CashRegister domain) {
        mapAbstractAuditFields(dto, domain.audit());
    }
}
