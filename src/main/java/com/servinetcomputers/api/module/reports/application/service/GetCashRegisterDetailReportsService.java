package com.servinetcomputers.api.module.reports.application.service;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.reports.application.usecase.GetCashRegisterDetailReportsUseCase;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetCashRegisterDetailReportsService implements GetCashRegisterDetailReportsUseCase {
    private final TransactionDetailRepository transactionDetailRepository;
    private final ExpenseRepository expenseRepository;

    @Transactional(readOnly = true)
    @Override
    public CashRegisterDetailReportsDto call(CashRegisterDetailResponse cashRegisterDetail) {
        final var cashRegisterDetailId = cashRegisterDetail.getId();

        final var finalBaseDto = cashRegisterDetail.getDetailFinalBase();

        final var transactionsAmount = 0; // temporal

        final var initialBase = cashRegisterDetail.getDetailInitialBase().calculate();
        final var finalBase = finalBaseDto != null ? finalBaseDto.calculate() : 0;

        var deposits = transactionDetailRepository.sumValuesByCashRegisterDetailIdAndType(cashRegisterDetailId, TransactionDetailType.DEPOSIT);
        var withdrawals = transactionDetailRepository.sumValuesByCashRegisterDetailIdAndType(cashRegisterDetailId, TransactionDetailType.WITHDRAWAL);

        var expenses = expenseRepository.sumValuesByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, false);
        var discounts = expenseRepository.sumValuesByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, true);

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
