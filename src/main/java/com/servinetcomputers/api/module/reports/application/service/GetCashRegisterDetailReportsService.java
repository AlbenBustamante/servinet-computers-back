package com.servinetcomputers.api.module.reports.application.service;

import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositCashRegisterDetailPersistenceAdapter;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailReportsDto;
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
    private final BankDepositCashRegisterDetailPersistenceAdapter bankDepositCashRegisterDetailPersistenceAdapter;
    private final TransactionDetailRepository transactionDetailRepository;
    private final ExpenseRepository expenseRepository;
    private final CashTransferRepository cashTransferRepository;

    @Transactional(readOnly = true)
    @Override
    public CashRegisterDetailReportsDto call(CashRegisterDetailDto cashRegisterDetail) {
        final var cashRegisterDetailId = cashRegisterDetail.getId();

        final var finalBaseDto = cashRegisterDetail.getDetailFinalBase();

        final var initialBase = cashRegisterDetail.getDetailInitialBase().calculate();
        final var finalBase = finalBaseDto != null ? finalBaseDto.calculate() : 0;

        var deposits = transactionDetailRepository.sumValuesByCashRegisterDetailIdAndType(cashRegisterDetailId, TransactionDetailType.DEPOSIT);
        var withdrawals = transactionDetailRepository.sumValuesByCashRegisterDetailIdAndType(cashRegisterDetailId, TransactionDetailType.WITHDRAWAL);

        final var bankDeposits = bankDepositCashRegisterDetailPersistenceAdapter.sumValuesByCashRegisterDetailId(cashRegisterDetailId);

        final var expenses = expenseRepository.sumValuesByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, false);
        final var discounts = expenseRepository.sumValuesByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, true);

        final var transfersSent = cashTransferRepository.sumAllBySenderIdAndType(cashRegisterDetailId, CashBoxType.CASH_REGISTER);
        final var transfersReceived = cashTransferRepository.sumAllByReceiverIdAndType(cashRegisterDetailId, CashBoxType.CASH_REGISTER);

        final var earnings = transactionDetailRepository.sumCommissionByCashRegisterDetailId(cashRegisterDetailId);

        deposits += transfersReceived + earnings;
        withdrawals += bankDeposits + expenses + discounts + transfersSent;

        final var balance = initialBase + deposits - withdrawals;
        final var discrepancy = finalBase - balance;

        final var transactionsAmount = transactionDetailRepository.countByCashRegisterDetailId(cashRegisterDetailId);

        return CashRegisterDetailReportsDto.builder()
                .cashRegisterDetail(cashRegisterDetail)
                .transactionsAmount(transactionsAmount)
                .initialBase(initialBase)
                .finalBase(finalBase)
                .deposits(deposits)
                .withdrawals(withdrawals)
                .bankDeposits(bankDeposits)
                .expenses(expenses)
                .discounts(discounts)
                .transfersSent(transfersSent)
                .transfersReceived(transfersReceived)
                .earnings(earnings)
                .balance(balance)
                .discrepancy(discrepancy)
                .build();
    }
}
