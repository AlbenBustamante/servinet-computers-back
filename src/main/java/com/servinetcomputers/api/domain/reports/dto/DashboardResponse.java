package com.servinetcomputers.api.domain.reports.dto;

import com.servinetcomputers.api.domain.platform.dto.PlatformStatsDto;

import java.util.List;

/**
 * The Admin Dashboard Reports dto model for responses.
 */
public record DashboardResponse(
        int totalBalance,
        List<PlatformStatsDto> platformsStats,
        int platformBalancesTotal
) {
}
