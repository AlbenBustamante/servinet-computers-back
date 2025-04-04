package com.servinetcomputers.api.module.transaction.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;

/**
 * Delete a current transaction detail by the ID.
 * <p>Receives the {@code TransactionDetail ID}.</p>
 * <p>Receives a temporal code.</p>
 * <p>Returns true if the transaction was successful, otherwise, false if the code is wrong or the id is not found.</p>
 */
public interface DeleteTransactionDetailUseCase extends UseCaseBiParam<Boolean, Integer, Integer> {
}
