package com.servinetcomputers.api.module.cashtransfer.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseTriParamWithoutReturn;

/**
 * Removes a current cash transfer by the ID.
 *
 * <p>Receives the {@code CashRegisterDetail ID}.</p>
 * <p>Receives the {@code CashTransfer ID}.</p>
 * <p>Receives the {@code TempCode}.</p>
 */
public interface DeleteCashTransferUseCase extends UseCaseTriParamWithoutReturn<Integer, Integer, Integer> {
}