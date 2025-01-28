package com.servinetcomputers.api.module.reports.dto;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformStatsDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeResponse;

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
