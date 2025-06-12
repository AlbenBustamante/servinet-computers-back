package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.DetailedCashRegisterReportsDto;

/**
 * Get all detailed movements made by a cash register detail by its ID.
 */
public interface GetDetailedReportsByIdUseCase extends UseCase<DetailedCashRegisterReportsDto, Integer> {
}
