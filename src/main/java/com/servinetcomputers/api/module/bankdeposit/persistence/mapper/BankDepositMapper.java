package com.servinetcomputers.api.module.bankdeposit.persistence.mapper;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDeposit;
import com.servinetcomputers.api.module.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.module.expense.persistence.mapper.ExpenseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CashRegisterDetailMapper.class, ExpenseMapper.class})
public interface BankDepositMapper {
    BankDepositDto toDto(BankDeposit entity);

    List<BankDepositDto> toDto(List<BankDeposit> entities);

    @Mapping(target = "cashRegisterDetail", source = "cashRegisterDetailDto")
    @Mapping(target = "expense", source = "expenseDto")
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    BankDeposit toEntity(CreateBankDepositDto dto);
}
