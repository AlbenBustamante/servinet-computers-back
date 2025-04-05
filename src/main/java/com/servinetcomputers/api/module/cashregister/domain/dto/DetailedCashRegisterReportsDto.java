package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogResponse;

import java.util.List;

public record DetailedCashRegisterReportsDto(
        CashRegisterDetailReportsDto reports,
        DetailedCashRegisterTransactionsDto transactions,
        List<ChangeLogResponse> changeLogs
) {
}
