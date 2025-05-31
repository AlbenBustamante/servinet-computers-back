package com.servinetcomputers.api.module.user.domain.dto;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;

import java.util.List;

public record JourneyDto(
        CashRegisterDetailDto cashRegisterDetail,
        List<ExpenseDto> discounts,
        Integer totalOfDiscounts,
        String totalOfHours
) {
}
