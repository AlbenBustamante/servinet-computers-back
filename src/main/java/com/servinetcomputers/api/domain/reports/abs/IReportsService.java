package com.servinetcomputers.api.domain.reports.abs;

import com.servinetcomputers.api.domain.reports.dto.ReportsResponse;

/**
 * The Admin Dashboard Reports uses case.
 */
public interface IReportsService {

    /**
     * Get the cashier/supervisor reports of the day.
     *
     * @param code the cashier/supervisor code.
     * @return a {@link ReportsResponse} with the results.
     */
    ReportsResponse getReports(String code);

}
