package com.servinetcomputers.api.module.cashregister.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Get all details of a cash register by its ID.
 */
public interface GetAllMovementsUseCase extends UseCaseBiParam<List<CashRegisterDetailDto>, Integer, LocalDate> {
}
