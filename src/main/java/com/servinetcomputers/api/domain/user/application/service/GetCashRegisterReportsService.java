package com.servinetcomputers.api.domain.user.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.cashregister.domain.dto.MyCashRegistersReports;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.domain.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.domain.user.application.usecase.GetCashRegisterReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetCashRegisterReportsService implements GetCashRegisterReportsUseCase {
    private final CashRegisterDetailRepository repository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final ExpenseRepository expenseRepository;
    private final DateTimeService dateTimeService;

    @Override
    public MyCashRegistersReports call(Integer param) {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final var details = repository.getAllByUserId(param, startDate, endDate);
        final List<CashRegisterDetailReportsDto> reports = new ArrayList<>(details.size());

        var total = CashRegisterDetailReportsDto.empty(details.get(0));

        for (final var cashRegisterDetail : details) {
            final var report = getCashRegistersReports(cashRegisterDetail);

            reports.add(report);
            total = total.sum(report);
        }

        return new MyCashRegistersReports(reports, total);
    }

    private CashRegisterDetailReportsDto getCashRegistersReports(CashRegisterDetailResponse cashRegisterDetail) {
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
