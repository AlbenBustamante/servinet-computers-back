package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;

public record UpdateCashRegisterDto(String description, CashRegisterStatus status) {
}
