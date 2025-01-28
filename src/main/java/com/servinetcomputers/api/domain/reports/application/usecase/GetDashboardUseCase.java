package com.servinetcomputers.api.domain.reports.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.domain.reports.dto.DashboardResponse;

/**
 * Get the admin reports of the day.
 */
public interface GetDashboardUseCase extends UseCaseWithoutParam<DashboardResponse> {
}
