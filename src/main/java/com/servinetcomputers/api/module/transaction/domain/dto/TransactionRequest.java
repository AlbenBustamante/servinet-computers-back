package com.servinetcomputers.api.module.transaction.domain.dto;

import com.servinetcomputers.api.core.util.enums.TransactionType;

public record TransactionRequest(
        String description,
        TransactionType type
) {
}
