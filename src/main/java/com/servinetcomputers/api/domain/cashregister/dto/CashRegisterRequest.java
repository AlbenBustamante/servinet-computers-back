package com.servinetcomputers.api.domain.cashregister.dto;

import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;

public record CashRegisterRequest(int numeral, CashRegisterStatus status) {
}
