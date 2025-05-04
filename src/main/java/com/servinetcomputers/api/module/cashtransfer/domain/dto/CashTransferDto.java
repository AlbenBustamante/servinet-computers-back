package com.servinetcomputers.api.module.cashtransfer.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.ModelResponse;
import com.servinetcomputers.api.module.base.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CashTransferDto extends ModelResponse {
    private final int value;
    private final int receiverId;
    private final int senderId;
    private final boolean received;
    private final String receiver;
    private final String sender;
    private final CashBoxType receiverType;
    private final CashBoxType senderType;
    private final BaseDto safeBase;

    public CashTransferDto(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                           int value, int receiverId, int senderId, boolean received, String receiver, String sender, CashBoxType receiverType, CashBoxType senderType, BaseDto safeBase) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.value = value;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.received = received;
        this.receiver = receiver;
        this.sender = sender;
        this.receiverType = receiverType;
        this.senderType = senderType;
        this.safeBase = safeBase;
    }

    public CashTransferDto copyWithDetails(boolean received, String receiver, String sender) {
        return new CashTransferDto(
                super.getId(),
                super.isEnabled(),
                super.getCreatedDate(),
                super.getModifiedDate(),
                super.getCreatedBy(),
                super.getModifiedBy(),
                value,
                receiverId,
                senderId,
                received,
                receiver,
                sender,
                receiverType,
                senderType,
                safeBase
        );
    }

    public CashTransferDto copy() {
        return copyWithDetails(received, receiver, sender);
    }
}
