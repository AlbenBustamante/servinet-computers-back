package com.servinetcomputers.api.module.reports.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.security.service.UserLoggedService;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import com.servinetcomputers.api.module.reports.application.usecase.GetDetailedTransactionsUseCase;
import com.servinetcomputers.api.module.reports.dto.ReportsResponse;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetDetailedTransactionsService implements GetDetailedTransactionsUseCase {
    private final TransactionDetailRepository transactionDetailRepository;
    private final PlatformTransferRepository platformTransferRepository;
    private final ExpenseRepository expenseRepository;
    private final DateTimeService dateTimeService;
    private final UserLoggedService userLoggedService;

    /**
     * Get the cashier/supervisor reports of the day.
     *
     * @return a {@link ReportsResponse} with the results.
     */
    @Transactional(readOnly = true)
    @Override
    public ReportsResponse call() {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();
        final var code = userLoggedService.code();

        final var transactions = transactionDetailRepository.getAllByCodeBetween(code, startDate, endDate);
        final var transfers = platformTransferRepository.getAllByCodeBetween(code, startDate, endDate);
        final var expenses = expenseRepository.getAllByDiscountAndCodeCodeBetween(false, code, startDate, endDate);
        final var discounts = expenseRepository.getAllByDiscountAndCodeCodeBetween(true, code, startDate, endDate);

        final var reports = ReportsResponse
                .builder()
                .transactions(transactions)
                .platformTransfers(transfers)
                .expenses(expenses)
                .discounts(discounts);

        return reports.build();
    }
}
