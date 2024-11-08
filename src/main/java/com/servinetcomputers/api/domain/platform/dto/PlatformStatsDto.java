package com.servinetcomputers.api.domain.platform.dto;

public record PlatformStatsDto(
        int platformId,
        int initialBalance,
        int finalBalance,
        int transfersAmount,
        int transfersTotal,
        int total
) {
}
