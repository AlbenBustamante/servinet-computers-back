package com.servinetcomputers.api.module.transaction.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.dto.UpdateTransactionDetailDto;

/**
 * Update all or some properties of an existing and enabled transaction detail.
 * <p>Receives the {@code Transaction Detail ID}.</p>
 * <p>Receives an {@link UpdateTransactionDetailDto}.</p>
 * <p>Returns a {@link TransactionDetailDto}</p>
 */
public interface UpdateTransactionDetailUseCase extends UseCaseBiParam<TransactionDetailDto, Integer, UpdateTransactionDetailDto> {
}
