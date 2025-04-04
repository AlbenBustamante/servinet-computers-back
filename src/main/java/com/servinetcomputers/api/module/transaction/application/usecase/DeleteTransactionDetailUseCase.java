package com.servinetcomputers.api.module.transaction.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParamWithoutReturn;

/**
 * Delete a current transaction detail by the ID.
 * <p>Receives the {@code TransactionDetail ID}.</p>
 * <p>Receives a temporal code.</p>
 */
public interface DeleteTransactionDetailUseCase extends UseCaseBiParamWithoutReturn<Integer, Integer> {
}
