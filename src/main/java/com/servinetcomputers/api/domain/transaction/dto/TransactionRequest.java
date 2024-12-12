package com.servinetcomputers.api.domain.transaction.dto;

import com.servinetcomputers.api.domain.transaction.util.TransactionType;

public record TransactionRequest(
        String description,
        TransactionType type
) {
}
