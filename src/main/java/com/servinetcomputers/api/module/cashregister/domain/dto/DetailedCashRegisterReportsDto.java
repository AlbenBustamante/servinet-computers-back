package com.servinetcomputers.api.module.cashregister.domain.dto;

public record DetailedCashRegisterReportsDto(
        CashRegisterDetailReportsDto reports,
        DetailedCashRegisterTransactionsDto transactions
) {
}
