package com.servinetcomputers.api.module.bankdeposit.persistence.mapper;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateDepositorDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.DepositorDto;
import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDepositCashRegisterDetail;
import com.servinetcomputers.api.module.cashregister.persistence.mapper.CashRegisterDetailMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BankDepositMapper.class, CashRegisterDetailMapper.class})
public interface DepositorMapper {
    @Mapping(target = "id", source = "id.cashRegisterDetailId")
    @Mapping(target = "fullName", expression = "java(entity.getCashRegisterDetail().getFullName())")
    @Mapping(target = "date", source = "createdDate")
    DepositorDto toDto(BankDepositCashRegisterDetail entity);

    List<DepositorDto> toDto(List<BankDepositCashRegisterDetail> entities);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "cashRegisterDetail", ignore = true)
    @Mapping(target = "bankDeposit", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    BankDepositCashRegisterDetail toEntity(CreateDepositorDto dto);
}
