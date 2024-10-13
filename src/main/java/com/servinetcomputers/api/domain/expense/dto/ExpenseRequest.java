package com.servinetcomputers.api.domain.expense.dto;

public record ExpenseRequest(
        int cashRegisterDetailId,
        String description,
        int value,
        boolean discount
) {
}
