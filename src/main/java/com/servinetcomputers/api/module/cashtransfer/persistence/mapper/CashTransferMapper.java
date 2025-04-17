package com.servinetcomputers.api.module.cashtransfer.persistence.mapper;

import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.persistence.entity.CashTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CashTransferMapper {
    @Mapping(target = "received", ignore = true)
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    CashTransferDto toDto(CashTransfer entity);

    List<CashTransferDto> toDto(List<CashTransfer> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    CashTransfer toEntity(CreateCashTransferDto dto);

    CashTransfer toEntity(CashTransferDto dto);
}
