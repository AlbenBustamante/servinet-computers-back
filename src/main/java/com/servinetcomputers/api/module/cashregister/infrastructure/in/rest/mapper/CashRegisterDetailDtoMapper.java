package com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.mapper;

import com.servinetcomputers.api.core.audit.infra.AuditableDtoMapper;
import com.servinetcomputers.api.module.cashregister.domain.CashRegisterDetail;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapeador de dominio a dto para los movimientos de caja registradora.
 */
public abstract class CashRegisterDetailDtoMapper extends AuditableDtoMapper {
    public abstract CashRegisterDetailDto toDto(CashRegisterDetail domain);

    public abstract List<CashRegisterDetailDto> toDto(List<CashRegisterDetail> domain);

    @AfterMapping
    protected void mapAuditFields(@MappingTarget CashRegisterDetailDto dto, CashRegisterDetail domain) {
        mapAbstractAuditFields(dto, domain.audit());
    }
}
