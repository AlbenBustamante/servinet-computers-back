package com.servinetcomputers.api.domain.dashboard.abs;

import com.servinetcomputers.api.domain.dashboard.dto.DashboardResponse;

/**
 * The Admin Dashboard Reports uses case.
 */
public interface IDashboardService {

    /**
     * Get the user reports of the day.
     *
     * @return a {@link DashboardResponse} with the results.
     */
    DashboardResponse getDashboard();

}
