package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailMovementsDto;

/**
 * Get all detailed movements made by a cash register detail by its ID.
 */
public interface GetCashRegisterDetailReportsAndMovementsUseCase extends UseCase<CashRegisterDetailMovementsDto, Integer> {
}
