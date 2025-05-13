package com.servinetcomputers.api.module.platform.domain.dto;

import lombok.Builder;

@Builder
public record PlatformStatsDto(
        int platformId,
        String platformName,
        int initialBalance,
        int finalBalance,
        int transfersAmount,
        int transfersTotal,
        int bankDepositsAmount,
        int bankDepositsTotal,
        int total
) {
}
