package com.servinetcomputers.api.module.cashregister.persistence.mapper;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDto;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CashRegisterMapper {
    CashRegisterDto toDto(CashRegisterEntity entity);

    List<CashRegisterDto> toDto(List<CashRegisterEntity> entities);

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    CashRegisterEntity toEntity(CreateCashRegisterDto dto);

    CashRegisterEntity toEntity(CashRegisterDto dto);
}
