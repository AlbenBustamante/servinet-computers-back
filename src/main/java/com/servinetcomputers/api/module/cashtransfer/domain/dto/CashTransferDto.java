package com.servinetcomputers.api.module.cashtransfer.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CashTransferDto extends AuditableDto<Integer> {
    private Integer value, receiverId, senderId;
    private Boolean received;
    private String receiver, sender;
    private CashBoxType receiverType, senderType;
    private BaseDto safeBase;

    public CashTransferDto copy() {
        final var dto = new CashTransferDto(
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

        dto.setId(super.getId());
        dto.setEnabled(super.getEnabled());
        dto.setCreatedBy(super.getCreatedBy());
        dto.setCreatedDate(super.getCreatedDate());
        dto.setModifiedBy(super.getModifiedBy());
        dto.setModifiedDate(super.getModifiedDate());

        return dto;
    }

    public CashTransferDto copyWithDetails(Boolean received, String receiver, String sender) {
        final var dto = new CashTransferDto(
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

        dto.setId(super.getId());
        dto.setEnabled(super.getEnabled());
        dto.setCreatedBy(super.getCreatedBy());
        dto.setCreatedDate(super.getCreatedDate());
        dto.setModifiedBy(super.getModifiedBy());
        dto.setModifiedDate(super.getModifiedDate());

        return dto;
    }
}
