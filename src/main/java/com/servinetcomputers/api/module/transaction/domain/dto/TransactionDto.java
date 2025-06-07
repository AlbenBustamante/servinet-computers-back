package com.servinetcomputers.api.module.transaction.domain.dto;

import com.servinetcomputers.api.core.audit.infra.AuditableDto;
import com.servinetcomputers.api.core.util.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDto extends AuditableDto<Integer> {
    private String description;
    private TransactionType type;
    private int uses;

    public void addUse() {
        uses++;
    }
}
