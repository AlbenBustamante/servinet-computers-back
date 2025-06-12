package com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto;

import java.util.List;

public record AlreadyExistsCashRegisterDetailDto(
        boolean alreadyExists,
        MyCashRegistersReports myCashRegisters,
        List<CashRegisterDto> availableCashRegisters
) {
}
