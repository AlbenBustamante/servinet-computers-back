package com.servinetcomputers.api.domain.cashregister.dto;

public record CashRegisterDetailReportsDto(
        CashRegisterDetailResponse cashRegisterDetail,
        int transactionsAmount,
        int initialBase,
        int finalBase,
        int deposits,
        int withdrawals,
        int expenses,
        int credits,
        int balance,
        int discrepancy
) {
}
