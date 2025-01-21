package com.servinetcomputers.api.domain.expense.domain.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExpenseResponse extends ModelResponse {
    private final int cashRegisterDetailId, value;
    private final String description;
    private final boolean discount;

    public ExpenseResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                           int cashRegisterDetailId, int value, String description, boolean discount) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.cashRegisterDetailId = cashRegisterDetailId;
        this.value = value;
        this.description = description;
        this.discount = discount;
    }
}
