package com.servinetcomputers.api.module.transaction.domain.dto;

import com.servinetcomputers.api.core.util.enums.TransactionType;

public record CreateTransactionDto(
        String description,
        TransactionType type
) {
}
