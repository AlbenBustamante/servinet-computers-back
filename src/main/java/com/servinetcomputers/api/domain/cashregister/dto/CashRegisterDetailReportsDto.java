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
    public CashRegisterDetailReportsDto sum(CashRegisterDetailReportsDto reports) {
        return new CashRegisterDetailReportsDto(
                cashRegisterDetail,
                transactionsAmount + reports.transactionsAmount,
                initialBase + reports.initialBase,
                finalBase + reports.finalBase,
                deposits + reports.deposits,
                withdrawals + reports.withdrawals,
                expenses + reports.expenses,
                credits + reports.credits,
                balance + reports.balance,
                discrepancy + reports.discrepancy
        );
    }
}
