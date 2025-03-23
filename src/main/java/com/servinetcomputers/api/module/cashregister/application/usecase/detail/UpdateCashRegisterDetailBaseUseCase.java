package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.cashregister.domain.dto.UpdateCashRegisterDetailBaseDto;

/**
 * Update the initial or final base of a Cash Register Detail by its ID.
 *
 * <p>Receives the Cash Register Detail ID.</p>
 * <p>Receives the {@link UpdateCashRegisterDetailBaseDto} model.</p>
 * <p>Returns the {@link CashRegisterDetailResponse} model.</p>
 *
 * @see UseCaseBiParam
 * @see UpdateCashRegisterDetailBaseDto
 * @see CashRegisterDetailResponse
 */
public interface UpdateCashRegisterDetailBaseUseCase extends UseCaseBiParam<CashRegisterDetailResponse, Integer, UpdateCashRegisterDetailBaseDto> {
}
