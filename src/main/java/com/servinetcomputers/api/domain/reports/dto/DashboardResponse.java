package com.servinetcomputers.api.domain.reports.dto;

import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.platform.dto.PlatformStatsDto;
import com.servinetcomputers.api.domain.safes.dto.SafeResponse;

import java.util.List;

/**
 * The Admin Dashboard Reports dto model for responses.
 */
public record DashboardResponse(
        int totalBalance,
        List<PlatformStatsDto> platformsStats,
        int platformBalancesTotal,
        List<CashRegisterDetailResponse> cashRegisterDetails,
        int cashRegistersTotal,
        List<SafeResponse> safes,
        int safesTotal
) {
}
