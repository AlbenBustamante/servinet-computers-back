package com.servinetcomputers.api.module.cashregister.domain.dto;

import java.util.List;

public record AdmCashRegistersDto(
        List<CashRegisterDetailDto> currentCashRegisters,
        List<CashRegisterDetailDto> pendingCashRegisters,
        List<CashRegisterDetailDto> remainingCashRegisters
) {
}
