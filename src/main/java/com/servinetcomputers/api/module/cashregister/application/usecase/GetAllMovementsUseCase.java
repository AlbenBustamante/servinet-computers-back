package com.servinetcomputers.api.module.cashregister.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;

import java.util.List;

/**
 * Get all details of a cash register by its ID.
 */
public interface GetAllMovementsUseCase extends UseCase<List<CashRegisterDetailDto>, Integer> {
}
