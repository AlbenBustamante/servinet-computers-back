package com.servinetcomputers.api.module.bankdeposit.persistence.mapper;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDeposit;
import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDepositPayment;
import com.servinetcomputers.api.module.platform.persistence.mapper.PlatformMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PlatformMapper.class, BankDepositMapper.class})
public interface BankDepositPaymentMapper {
    @Mapping(target = "bankDepositId", source = "bankDeposit.id")
    BankDepositPaymentDto toDto(BankDepositPayment entity);

    List<BankDepositPaymentDto> toDto(List<BankDepositPayment> entities);

    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "bankDeposit", ignore = true)
    BankDepositPayment toEntity(CreateBankDepositPaymentDto dto);

    @Mapping(target = "bankDeposit", expression = "java(toBankDeposit(dto.getBankDepositId()))")
    BankDepositPayment toEntity(BankDepositPaymentDto dto);

    default BankDeposit toBankDeposit(Integer bankDepositId) {
        if (bankDepositId == null) {
            return null;
        }

        final var bankDeposit = new BankDeposit();
        bankDeposit.setId(bankDepositId);

        return bankDeposit;
    }
}
