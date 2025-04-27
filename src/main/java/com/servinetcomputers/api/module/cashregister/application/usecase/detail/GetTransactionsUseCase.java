package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import org.springframework.data.domain.Pageable;

/**
 * Gets the transactions made by a cash register detail wrapped in a page.
 *
 * <p>Receives the {@code CashRegisterDetail ID}.</p>
 * <p>Receives the {@link Pageable} data.</p>
 * <p>Returns a {@link PageResponse} with the pagination and results.</p>
 */
public interface GetTransactionsUseCase extends UseCaseBiParam<PageResponse<TransactionDetailResponse>, Integer, Pageable> {
}
