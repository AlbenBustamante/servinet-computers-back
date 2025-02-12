package com.servinetcomputers.api.module.cashtransfer.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashBoxType;

public record CashTransferDto(
        int id,
        int value,
        int receiverId,
        int senderId,
        boolean received,
        String receiver,
        String sender,
        CashBoxType receiverType,
        CashBoxType senderType
) {
    public CashTransferDto copyWithDetails(boolean received, String receiver, String sender) {
        return new CashTransferDto(
                id,
                value,
                receiverId,
                senderId,
                received,
                receiver,
                sender,
                receiverType,
                senderType
        );
    }
}
