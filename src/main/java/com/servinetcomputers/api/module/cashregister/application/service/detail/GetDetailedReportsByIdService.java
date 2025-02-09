package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetDetailedReportsByIdUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.DetailedCashRegisterReportsDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.DetailedCashRegisterTransactionsDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.reports.application.usecase.GetCashRegisterDetailReportsUseCase;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class GetDetailedReportsByIdService implements GetDetailedReportsByIdUseCase {
    private final CashRegisterDetailRepository repository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final ExpenseRepository expenseRepository;
    private final GetCashRegisterDetailReportsUseCase getCashRegisterDetailReportsUseCase;

    /**
     * Get all detailed movements made by a cash register detail by its ID.
     *
     * @param id the cash register detail ID.
     * @return the detailed movements.
     */
    @Secured(value = ADMIN_AUTHORITY)
    @Transactional(readOnly = true)
    @Override
    public DetailedCashRegisterReportsDto call(Integer id) {
        final var cashRegisterDetail = repository.get(id)
                .orElseThrow(() -> new NotFoundException("No se encontró los movimientos con ID: #" + id));

        final var reports = getCashRegisterDetailReportsUseCase.call(cashRegisterDetail);
        final var transactions = transactionDetailRepository.getAllByCashRegisterDetailId(id);
        final var expenses = expenseRepository.getAllByCashRegisterDetailId(id);

        final var detailedTransactions = new DetailedCashRegisterTransactionsDto(transactions, expenses);

        return new DetailedCashRegisterReportsDto(reports, detailedTransactions);
    }
}
