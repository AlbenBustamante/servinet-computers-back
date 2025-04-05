package com.servinetcomputers.api.module.expense.domain.dto;

import com.servinetcomputers.api.module.ModelResponse;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExpenseResponse extends ModelResponse {
    private final int cashRegisterDetailId, value;
    private final String description;
    private final boolean discount;
    private CashRegisterDetailResponse cashRegisterDetail;

    public ExpenseResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                           int cashRegisterDetailId, int value, String description, boolean discount) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.cashRegisterDetailId = cashRegisterDetailId;
        this.value = value;
        this.description = description;
        this.discount = discount;
    }

    public ExpenseResponse copy() {
        final var response = new ExpenseResponse(
                super.getId(),
                super.isEnabled(),
                super.getCreatedDate(),
                super.getModifiedDate(),
                super.getCreatedBy(),
                super.getModifiedBy(),
                cashRegisterDetailId,
                value,
                description,
                discount
        );

        response.setCashRegisterDetail(cashRegisterDetail);

        return response;
    }
}
