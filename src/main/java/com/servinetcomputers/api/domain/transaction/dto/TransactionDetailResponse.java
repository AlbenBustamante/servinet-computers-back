package com.servinetcomputers.api.domain.transaction.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.transaction.util.TransactionDetailType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionDetailResponse extends ModelResponse {
    private final int cashRegisterDetailId, value, commission;
    private final String description;
    private final TransactionDetailType type;
    private final LocalDateTime date;

    public TransactionDetailResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                                     int cashRegisterDetailId, String description, int value, int commission, TransactionDetailType type, LocalDateTime date) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.cashRegisterDetailId = cashRegisterDetailId;
        this.description = description;
        this.value = value;
        this.commission = commission;
        this.type = type;
        this.date = date;
    }
}
