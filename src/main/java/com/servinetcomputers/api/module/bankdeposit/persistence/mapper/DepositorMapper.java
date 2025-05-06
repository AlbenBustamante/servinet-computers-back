package com.servinetcomputers.api.module.bankdeposit.persistence.mapper;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.DepositorDto;
import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDepositCashRegisterDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepositorMapper {
    @Mapping(target = "id", source = "id.cashRegisterDetailId")
    @Mapping(target = "fullName", expression = "java(entity.getCashRegisterDetail().getFullName())")
    @Mapping(target = "date", source = "createdDate")
    DepositorDto toDto(BankDepositCashRegisterDetail entity);

    List<DepositorDto> toDto(List<BankDepositCashRegisterDetail> entities);
}
