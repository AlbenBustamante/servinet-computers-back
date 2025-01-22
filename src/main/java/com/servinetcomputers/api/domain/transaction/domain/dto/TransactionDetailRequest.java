package com.servinetcomputers.api.domain.transaction.domain.dto;

import com.servinetcomputers.api.domain.transaction.util.TransactionDetailType;

import java.time.LocalDateTime;

public record TransactionDetailRequest(
        int cashRegisterDetailId,
        String description,
        TransactionDetailType type,
        int value,
        int commission,
        LocalDateTime date
) {
}
