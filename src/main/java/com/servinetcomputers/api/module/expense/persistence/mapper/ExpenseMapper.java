package com.servinetcomputers.api.module.expense.persistence.mapper;

import com.servinetcomputers.api.module.expense.persistence.entity.Expense;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseResponse toResponse(Expense entity);

    List<ExpenseResponse> toResponses(List<Expense> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "cashRegisterDetail", ignore = true)
    Expense toEntity(ExpenseRequest request);

}
