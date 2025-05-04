package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;

public record CreateCashRegisterDto(
        int numeral,
        String description,
        CashRegisterStatus status
) {
}
