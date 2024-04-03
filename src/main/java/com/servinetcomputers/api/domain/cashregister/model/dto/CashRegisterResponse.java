package com.servinetcomputers.api.domain.cashregister.model.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CashRegisterResponse extends ModelResponse {
    private final int numeral;
    private final CashRegisterStatus status;

    public CashRegisterResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, int numeral, CashRegisterStatus status) {
        super(id, enabled, createdDate, modifiedDate);
        this.numeral = numeral;
        this.status = status;
    }
}
