package com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto;

import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogDto;

import java.util.List;

public record DetailedCashRegisterReportsDto(
        CashRegisterDetailReportsDto reports,
        DetailedCashRegisterTransactionsDto transactions,
        List<ChangeLogDto> changeLogs
) {
}
