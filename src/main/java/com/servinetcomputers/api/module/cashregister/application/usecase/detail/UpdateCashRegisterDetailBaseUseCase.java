package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.UpdateCashRegisterDetailBaseDto;

/**
 * Update the initial or final base of a Cash Register Detail by its ID.
 *
 * <p>Receives the Cash Register Detail ID.</p>
 * <p>Receives the {@link UpdateCashRegisterDetailBaseDto} model.</p>
 * <p>Returns the {@link CashRegisterDetailDto} model.</p>
 *
 * @see UseCaseBiParam
 * @see UpdateCashRegisterDetailBaseDto
 * @see CashRegisterDetailDto
 */
public interface UpdateCashRegisterDetailBaseUseCase extends UseCaseBiParam<CashRegisterDetailDto, Integer, UpdateCashRegisterDetailBaseDto> {
}
