package com.servinetcomputers.api.domain.transaction.dto;

import com.servinetcomputers.api.domain.transaction.util.TransactionDetailType;

import java.time.LocalDateTime;

public record TransactionDetailRequest(
        int cashRegisterDetailId,
        Integer transactionId,
        String description,
        TransactionDetailType type,
        int value,
        int commission,
        LocalDateTime date
) {
}
