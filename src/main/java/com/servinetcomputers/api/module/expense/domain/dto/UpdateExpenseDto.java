package com.servinetcomputers.api.module.expense.domain.dto;

public record UpdateExpenseDto(
        String description,
        Integer value,
        Boolean discount,
        Integer tempCode
) {
}
