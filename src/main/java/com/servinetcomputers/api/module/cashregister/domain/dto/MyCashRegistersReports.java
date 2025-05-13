package com.servinetcomputers.api.module.cashregister.domain.dto;

import java.util.List;

public record MyCashRegistersReports(
        List<CashRegisterDetailReportsDto> cashRegisterDetailsReports,
        CashRegisterDetailReportsDto finalReport
) {
}
