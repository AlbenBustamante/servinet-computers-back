package com.servinetcomputers.api.domain.cashregister.domain.dto;

import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import lombok.Getter;

@Getter
public class UpdateCashRegisterDto extends CashRegisterRequest {
    private final int id;

    public UpdateCashRegisterDto(int numeral, String description, CashRegisterStatus status, int id) {
        super(numeral, description, status);
        this.id = id;
    }
}
