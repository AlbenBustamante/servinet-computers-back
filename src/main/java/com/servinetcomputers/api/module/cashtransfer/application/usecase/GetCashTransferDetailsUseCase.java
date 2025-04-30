package com.servinetcomputers.api.module.cashtransfer.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;

/**
 * Gets the most detailed data about a cash transfer: received, sender, receiver.
 * <p>Receives a {@link CashTransferDto} with simple data.</p>
 * <p>The {@code CashRegisterDetail ID} linked with the cash transfer.</p>
 * <p>Returns a detailed {@link CashTransferDto}.</p>
 */
public interface GetCashTransferDetailsUseCase extends UseCaseBiParam<CashTransferDto, CashTransferDto, Integer> {
}
