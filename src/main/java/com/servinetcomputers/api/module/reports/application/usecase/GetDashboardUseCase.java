package com.servinetcomputers.api.module.reports.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.reports.dto.DashboardResponse;

import java.time.LocalDate;

/**
 * Get the admin reports of the day.
 */
public interface GetDashboardUseCase extends UseCase<DashboardResponse, LocalDate> {
}
