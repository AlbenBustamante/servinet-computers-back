package com.servinetcomputers.api.module.transaction.application.usecase;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.transaction.domain.dto.CreateTransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import org.springframework.data.domain.Pageable;

/**
 * --- ORIENTED TO FRONT END ---
 * <p>Creates a new transaction detail and returns an updated list.</p>
 * <p>Receives the {@link CreateTransactionDetailDto} data.</p>
 * <p>Receives the {@link Pageable} to filter the responses.</p>
 * <p>Returns a {@link PageResponse} with the results.</p>
 */
public interface CreateTransactionDetailUseCase extends UseCaseBiParam<PageResponse<TransactionDetailDto>, CreateTransactionDetailDto, Pageable> {
}
