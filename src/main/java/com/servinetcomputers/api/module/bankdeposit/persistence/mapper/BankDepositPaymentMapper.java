package com.servinetcomputers.api.module.bankdeposit.persistence.mapper;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDepositPayment;
import com.servinetcomputers.api.module.platform.persistence.mapper.PlatformMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PlatformMapper.class)
public interface BankDepositPaymentMapper {
    @Mapping(target = "bankDepositId", source = "bankDeposit.id")
    BankDepositPaymentDto toDto(BankDepositPayment entity);

    List<BankDepositPaymentDto> toDto(List<BankDepositPayment> entities);
}
