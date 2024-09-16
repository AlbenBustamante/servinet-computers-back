package com.servinetcomputers.api.domain.cashregister.dto;

import java.util.List;

public record AlreadyExistsCashRegisterDetailDto(
        boolean alreadyExists,
        MyCashRegistersReports myCashRegisters,
        List<CashRegisterResponse> availableCashRegisters
) {
}
