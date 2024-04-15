package com.servinetcomputers.api.domain.cashregister.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.base.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CashRegisterBaseResponse extends ModelResponse {
    private final int cashRegisterDetailId;
    private final BaseDto initialBase, finalBase;

    public CashRegisterBaseResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                                    int cashRegisterDetailId, BaseDto initialBase, BaseDto finalBase) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.cashRegisterDetailId = cashRegisterDetailId;
        this.initialBase = initialBase;
        this.finalBase = finalBase;
    }
}
