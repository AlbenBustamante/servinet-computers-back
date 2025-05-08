package com.servinetcomputers.api.module.bankdeposit.persistence.mapper;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDepositPayment;
import com.servinetcomputers.api.module.platform.persistence.mapper.PlatformMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PlatformMapper.class, BankDepositMapper.class})
public interface BankDepositPaymentMapper {
    BankDepositPaymentDto toDto(BankDepositPayment entity);

    List<BankDepositPaymentDto> toDto(List<BankDepositPayment> entities);

    BankDepositPayment toEntity(BankDepositPaymentDto dto);
}
