package com.servinetcomputers.api.module.cashregister.domain.dto;

import java.util.List;

public record AdmCashRegistersDto(
        List<CashRegisterDetailResponse> currentCashRegisters,
        List<CashRegisterDetailResponse> pendingCashRegisters,
        List<CashRegisterDetailResponse> remainingCashRegisters
) {
}
