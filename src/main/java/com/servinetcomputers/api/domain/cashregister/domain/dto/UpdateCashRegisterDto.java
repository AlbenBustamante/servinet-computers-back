package com.servinetcomputers.api.domain.cashregister.domain.dto;

import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;

public record UpdateCashRegisterDto(int numeral, String description, CashRegisterStatus status) {
}
