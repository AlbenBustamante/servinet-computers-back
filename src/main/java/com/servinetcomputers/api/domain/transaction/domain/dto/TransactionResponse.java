package com.servinetcomputers.api.domain.transaction.domain.dto;

import com.servinetcomputers.api.core.util.enums.TransactionType;
import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionResponse extends ModelResponse {
    private final String description;
    private final TransactionType type;
    private int uses;

    public TransactionResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                               String description, TransactionType type, int uses) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.description = description;
        this.type = type;
        this.uses = uses;
    }

    public void addUse() {
        uses++;
    }
}
