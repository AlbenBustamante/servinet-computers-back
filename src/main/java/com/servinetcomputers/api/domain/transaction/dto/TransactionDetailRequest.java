package com.servinetcomputers.api.domain.transaction.dto;

import com.servinetcomputers.api.domain.transaction.util.TransactionDetailType;

public record TransactionDetailRequest(
        int cashRegisterDetailId,
        Integer transactionId,
        String description,
        TransactionDetailType type,
        int value,
        int commission
) {
}
