package com.servinetcomputers.api.module.platform.domain.dto;

/**
 * The balance's dto model for requests.
 */
public record CreatePlatformBalanceDto(
        Integer initialBalance,
        Integer finalBalance,
        PlatformDto platform
) {
}
