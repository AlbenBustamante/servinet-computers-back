package com.servinetcomputers.api.domain.reports.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.domain.reports.application.usecase.GetCashRegisterDetailReportsUseCase;
import com.servinetcomputers.api.domain.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCashRegisterDetailReportsService implements GetCashRegisterDetailReportsUseCase {
    private final TransactionDetailRepository transactionDetailRepository;
    private final ExpenseRepository expenseRepository;
    private final DateTimeService dateTimeService;

    @Override
    public CashRegisterDetailReportsDto call(CashRegisterDetailResponse cashRegisterDetail) {
        final var startDate = cashRegisterDetail.getCreatedDate();
        final var endDate = dateTimeService.now();
        final var code = cashRegisterDetail.getCreatedBy();

        final var finalBaseDto = cashRegisterDetail.getDetailFinalBase();

        final var transactionsAmount = 0; // temporal

        final var initialBase = cashRegisterDetail.getDetailInitialBase().calculate();
        final var finalBase = finalBaseDto != null ? finalBaseDto.calculate() : 0;

        var deposits = transactionDetailRepository.sumDeposits(code, startDate, endDate);
        var withdrawals = transactionDetailRepository.sumWithdrawals(code, startDate, endDate);

        var expenses = expenseRepository.sumExpenses(code, startDate, endDate);
        var discounts = expenseRepository.sumDiscounts(code, startDate, endDate);

        withdrawals += expenses + discounts;

        final var balance = initialBase + deposits - withdrawals - expenses - discounts;

        final var discrepancy = finalBase - balance;

        return new CashRegisterDetailReportsDto(
                cashRegisterDetail,
                transactionsAmount,
                initialBase,
                finalBase,
                deposits,
                withdrawals,
                expenses,
                discounts,
                balance,
                discrepancy
        );
    }
}
