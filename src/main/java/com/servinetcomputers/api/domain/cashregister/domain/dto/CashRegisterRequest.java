package com.servinetcomputers.api.domain.cashregister.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import lombok.Data;

@Data
public class CashRegisterRequest {
    private final int numeral;
    private final String description;
    private final CashRegisterStatus status;
}
