package com.servinetcomputers.api.domain.expense.domain.dto;

public record ExpenseRequest(
        int cashRegisterDetailId,
        String description,
        int value,
        boolean discount
) {
}
