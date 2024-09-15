package com.servinetcomputers.api.domain.cashregister.dto;

import java.util.List;

public record AlreadyExistsCashRegisterDetailDto(
        boolean alreadyExists,
        List<CashRegisterDetailResponse> myCashRegisters,
        List<CashRegisterResponse> availableCashRegisters
) {
}
