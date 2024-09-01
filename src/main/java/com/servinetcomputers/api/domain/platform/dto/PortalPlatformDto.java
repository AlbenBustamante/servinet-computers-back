package com.servinetcomputers.api.domain.platform.dto;

public record PortalPlatformDto(
        int platformId,
        String platformName,
        int initialBalance,
        int finalBalance,
        int transfersAmount,
        int transfersTotal
) {
}
