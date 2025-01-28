package com.servinetcomputers.api.module.expense.domain.dto;

public record ExpenseRequest(
        int cashRegisterDetailId,
        String description,
        int value,
        boolean discount
) {
}
