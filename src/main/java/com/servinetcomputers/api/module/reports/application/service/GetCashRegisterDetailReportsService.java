package com.servinetcomputers.api.module.reports.application.service;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
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
    private final CashTransferRepository cashTransferRepository;

    @Transactional(readOnly = true)
    @Override
    public CashRegisterDetailReportsDto call(CashRegisterDetailResponse cashRegisterDetail) {
        final var cashRegisterDetailId = cashRegisterDetail.getId();

        final var finalBaseDto = cashRegisterDetail.getDetailFinalBase();

        final var initialBase = cashRegisterDetail.getDetailInitialBase().calculate();
        final var finalBase = finalBaseDto != null ? finalBaseDto.calculate() : 0;

        var deposits = transactionDetailRepository.sumValuesByCashRegisterDetailIdAndType(cashRegisterDetailId, TransactionDetailType.DEPOSIT);
        var withdrawals = transactionDetailRepository.sumValuesByCashRegisterDetailIdAndType(cashRegisterDetailId, TransactionDetailType.WITHDRAWAL);

        var expenses = expenseRepository.sumValuesByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, false);
        var discounts = expenseRepository.sumValuesByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, true);

        final var transfersSent = cashTransferRepository.sumAllBySenderId(cashRegisterDetailId);
        final var transfersReceived = cashTransferRepository.sumAllByReceiverId(cashRegisterDetailId);

        deposits += transfersReceived;
        withdrawals += expenses + discounts + transfersSent;

        final var balance = initialBase + deposits - withdrawals - expenses - discounts;

        final var discrepancy = finalBase - balance;

        final var transactionsAmount = transactionDetailRepository.countById(cashRegisterDetailId);

        return CashRegisterDetailReportsDto.builder()
                .cashRegisterDetail(cashRegisterDetail)
                .transactionsAmount(transactionsAmount)
                .initialBase(initialBase)
                .finalBase(finalBase)
                .deposits(deposits)
                .withdrawals(withdrawals)
                .expenses(expenses)
                .discounts(discounts)
                .transfersSent(transfersSent)
                .transfersReceived(transfersReceived)
                .balance(balance)
                .discrepancy(discrepancy)
                .build();
    }
}
