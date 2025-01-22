package com.servinetcomputers.api.domain.transaction.domain.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.transaction.util.TransactionType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionResponse extends ModelResponse {
    private final String description;
    private final TransactionType type;

    public TransactionResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                               String description, TransactionType type) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.description = description;
        this.type = type;
    }
}
