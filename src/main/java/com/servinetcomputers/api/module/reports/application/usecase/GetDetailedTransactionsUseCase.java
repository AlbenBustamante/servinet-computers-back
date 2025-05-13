package com.servinetcomputers.api.module.reports.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.reports.dto.ReportsResponse;

/**
 * Get the cashier/supervisor reports of the day.
 */
public interface GetDetailedTransactionsUseCase extends UseCaseWithoutParam<ReportsResponse> {
}
