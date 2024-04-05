package com.servinetcomputers.api.domain.cashregister.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CashRegisterResponse extends ModelResponse {
    private final int numeral;
    private final String description;
    private final CashRegisterStatus status;

    public CashRegisterResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate,
                                int numeral, String description, CashRegisterStatus status) {
        super(id, enabled, createdDate, modifiedDate);
        this.numeral = numeral;
        this.description = description;
        this.status = status;
    }
}
