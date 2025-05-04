package com.servinetcomputers.api.module.transaction.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionDetailDto extends AuditableDto<Integer> {
    private int cashRegisterDetailId, value, commission;
    private String description;
    private TransactionDetailType type;
    private LocalDateTime date;
    private TransactionDto transaction;
    private CashRegisterDetailDto cashRegisterDetail;

    public TransactionDetailDto copy() {
        final var dto = new TransactionDetailDto(
                cashRegisterDetailId,
                value,
                commission,
                description,
                type,
                date,
                transaction,
                cashRegisterDetail
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
