package com.servinetcomputers.api.module.transaction.domain.dto;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;

import java.time.LocalDateTime;

public record UpdateTransactionDetailDto(
        String description,
        TransactionDetailType type,
        Integer value,
        Integer commission,
        LocalDateTime date,
        Integer tempCode
) {
}
