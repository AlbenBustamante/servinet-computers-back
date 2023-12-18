package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.response.DashboardResponse;

/**
 * The Admin Dashboard Reports uses case.
 */
public interface IDashboardService {

    /**
     * Get the user reports of the day.
     *
     * @param userId the ID of the user.
     * @return a {@link DashboardResponse} with the results.
     */
    DashboardResponse getReports(int userId);
    
}
