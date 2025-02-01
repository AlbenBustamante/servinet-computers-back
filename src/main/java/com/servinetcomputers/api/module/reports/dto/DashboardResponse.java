package com.servinetcomputers.api.module.reports.dto;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformStatsDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailResponse;

import java.util.List;

/**
 * The Admin Dashboard Reports dto model for responses.
 */
public record DashboardResponse(
        int totalBalance,
        int platformBalancesTotal,
        int cashRegistersTotal,
        int safesTotal,
        List<PlatformStatsDto> platformsStats,
        List<CashRegisterDetailResponse> cashRegisterDetails,
        List<SafeDetailResponse> safeDetails
) {
}
