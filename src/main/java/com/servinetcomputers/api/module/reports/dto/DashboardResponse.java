package com.servinetcomputers.api.module.reports.dto;

import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformStatsDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import lombok.Builder;

import java.util.List;

/**
 * The Admin Dashboard Reports dto model for responses.
 */
@Builder
public record DashboardResponse(
        int totalBalance,
        int transactionsAmount,
        int earnings,
        int expenses,
        int platformBalancesTotal,
        int cashRegistersTotal,
        int safesTotal,
        List<PlatformStatsDto> platformsStats,
        List<CashRegisterDetailDto> cashRegisterDetails,
        List<SafeDetailDto> safeDetails
) {
}
