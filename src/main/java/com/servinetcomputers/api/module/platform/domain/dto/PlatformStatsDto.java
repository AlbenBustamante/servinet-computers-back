package com.servinetcomputers.api.module.platform.domain.dto;

public record PlatformStatsDto(
        int platformId,
        String platformName,
        int initialBalance,
        int finalBalance,
        int transfersAmount,
        int transfersTotal,
        int total
) {
}
