package com.servinetcomputers.api.domain.cashregister.model.dto;

import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;

public record CashRegisterRequest(int numeral, CashRegisterStatus status) {
}
