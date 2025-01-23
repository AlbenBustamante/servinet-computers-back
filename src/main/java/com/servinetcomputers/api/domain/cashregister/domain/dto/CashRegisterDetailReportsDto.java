package com.servinetcomputers.api.domain.cashregister.domain.dto;

public record CashRegisterDetailReportsDto(
        CashRegisterDetailResponse cashRegisterDetail,
        int transactionsAmount,
        int initialBase,
        int finalBase,
        int deposits,
        int withdrawals,
        int expenses,
        int discounts,
        int balance,
        int discrepancy
) {
    public static CashRegisterDetailReportsDto empty(CashRegisterDetailResponse cashRegisterDetail) {
        return new CashRegisterDetailReportsDto(cashRegisterDetail, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public CashRegisterDetailReportsDto sum(CashRegisterDetailReportsDto reports) {
        return new CashRegisterDetailReportsDto(
                cashRegisterDetail,
                transactionsAmount + reports.transactionsAmount,
                initialBase + reports.initialBase,
                finalBase + reports.finalBase,
                deposits + reports.deposits,
                withdrawals + reports.withdrawals,
                expenses + reports.expenses,
                discounts + reports.discounts,
                balance + reports.balance,
                discrepancy + reports.discrepancy
        );
    }
}
