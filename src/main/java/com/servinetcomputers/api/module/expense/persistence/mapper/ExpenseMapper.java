package com.servinetcomputers.api.module.expense.persistence.mapper;

import com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.module.expense.domain.dto.CreateExpenseDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import com.servinetcomputers.api.module.expense.persistence.entity.ExpenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CashRegisterDetailMapper.class)
public interface ExpenseMapper {
    @Mapping(target = "cashRegisterDetailId", source = "cashRegisterDetail.id")
    ExpenseDto toDto(ExpenseEntity entity);

    List<ExpenseDto> toDto(List<ExpenseEntity> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    ExpenseEntity toEntity(CreateExpenseDto dto);

    ExpenseEntity toEntity(ExpenseDto dto);
}
