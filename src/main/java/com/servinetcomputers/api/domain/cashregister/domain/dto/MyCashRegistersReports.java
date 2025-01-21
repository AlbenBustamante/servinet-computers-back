package com.servinetcomputers.api.domain.cashregister.domain.dto;

import java.util.List;

public record MyCashRegistersReports(
        List<CashRegisterDetailReportsDto> cashRegisterDetailsReports,
        CashRegisterDetailReportsDto finalReport
) {
}
