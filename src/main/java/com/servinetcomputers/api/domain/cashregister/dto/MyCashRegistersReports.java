package com.servinetcomputers.api.domain.cashregister.dto;

import java.util.List;

public record MyCashRegistersReports(
        List<CashRegisterDetailReportsDto> cashRegisterDetailsReports,
        CashRegisterDetailReportsDto finalReport
) {
}
