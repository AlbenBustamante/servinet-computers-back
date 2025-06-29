package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashRegisterDetailReportsAndMovementsUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashTransfersByIdUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailMovementsDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.DetailedCashRegisterTransactionsDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.changelog.domain.repository.ChangeLogRepository;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class GetCashRegisterDetailReportsAndMovementsService implements GetCashRegisterDetailReportsAndMovementsUseCase {
    private final CashRegisterDetailRepository repository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final ExpenseRepository expenseRepository;
    private final ChangeLogRepository changeLogRepository;
    private final com.servinetcomputers.api.module.reports.application.usecase.GetCashRegisterDetailReportsUseCase getCashRegisterDetailReportsUseCase;
    private final GetCashTransfersByIdUseCase getCashTransfersByIdUseCase;

    /**
     * Get all detailed movements made by a cash register detail by its ID.
     *
     * @param cashRegisterDetailId the cash register detail ID.
     * @return the detailed movements.
     */
    @Secured(value = ADMIN_AUTHORITY)
    @Transactional(readOnly = true)
    @Override
    public CashRegisterDetailMovementsDto call(Integer cashRegisterDetailId) {
        final var cashRegisterDetail = repository.get(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró los movimientos con ID: #" + cashRegisterDetailId));

        final var reports = getCashRegisterDetailReportsUseCase.call(cashRegisterDetail);
        final var transactions = transactionDetailRepository.getAllByCashRegisterDetailId(cashRegisterDetailId);
        final var expenses = expenseRepository.getAllByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, false);
        final var discounts = expenseRepository.getAllByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, true);
        final var transfers = getCashTransfersByIdUseCase.call(cashRegisterDetailId, Pageable.unpaged());

        final var detailedTransactions = new DetailedCashRegisterTransactionsDto(transactions, expenses, discounts, transfers.getContent());

        final var changeLogs = changeLogRepository.getAllByCashRegisterDetailId(cashRegisterDetailId);

        return new CashRegisterDetailMovementsDto(reports, detailedTransactions, changeLogs);
    }
}
