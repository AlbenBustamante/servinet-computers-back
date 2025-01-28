package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import com.servinetcomputers.api.module.ModelResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CashRegisterResponse extends ModelResponse {
    private final int numeral;
    private String description;
    private CashRegisterStatus status;

    public CashRegisterResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy,
                                String modifiedBy, int numeral, String description, CashRegisterStatus status) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.numeral = numeral;
        this.description = description;
        this.status = status;
    }
}
