package com.servinetcomputers.api.module.cashtransfer.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashBoxType;

public record CreateCashTransferDto(
        int value,
        Integer senderId,
        Integer receiverId,
        CashBoxType senderType,
        CashBoxType receiverType
) {
}
