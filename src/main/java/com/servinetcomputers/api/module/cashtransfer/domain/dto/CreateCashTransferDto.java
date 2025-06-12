package com.servinetcomputers.api.module.cashtransfer.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.base.Base;

public record CreateCashTransferDto(
        int value,
        Integer senderId,
        Integer receiverId,
        CashBoxType senderType,
        CashBoxType receiverType,
        Integer safeDetailId,
        Base safeBase,
        Integer safeDenomination,
        Integer safeAmount,
        Integer currentCashRegisterDetailId
) {
}
