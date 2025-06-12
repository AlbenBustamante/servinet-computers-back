package com.servinetcomputers.api.module.expense.domain.dto;

import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CreateExpenseDto {
    private final int cashRegisterDetailId;
    private final String description;
    private final int value;
    private final boolean discount, administrative;
    private CashRegisterDetailDto cashRegisterDetail;
}
