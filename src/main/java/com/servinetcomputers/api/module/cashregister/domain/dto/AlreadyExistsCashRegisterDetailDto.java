package com.servinetcomputers.api.module.cashregister.domain.dto;

import java.util.List;

public record AlreadyExistsCashRegisterDetailDto(
        boolean alreadyExists,
        MyCashRegistersReports myCashRegisters,
        List<CashRegisterResponse> availableCashRegisters
) {
}
