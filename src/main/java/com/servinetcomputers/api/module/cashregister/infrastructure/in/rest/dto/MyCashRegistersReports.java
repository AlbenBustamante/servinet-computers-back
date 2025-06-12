package com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto;

import java.util.List;

public record MyCashRegistersReports(
        List<CashRegisterDetailReportsDto> cashRegisterDetailsReports,
        CashRegisterDetailReportsDto finalReport
) {
}
