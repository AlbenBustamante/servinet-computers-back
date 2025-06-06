package com.servinetcomputers.api.module.platform.domain.dto;

public record PortalPlatformDto(
        int platformId,
        String platformName,
        int platformBalanceId,
        int initialBalance,
        int finalBalance,
        int transfersAmount,
        int transfersTotal
) {
}
