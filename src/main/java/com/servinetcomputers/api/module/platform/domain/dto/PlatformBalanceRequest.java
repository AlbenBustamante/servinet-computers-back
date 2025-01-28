package com.servinetcomputers.api.module.platform.domain.dto;

/**
 * The balance's dto model for requests.
 */
public record PlatformBalanceRequest(int platformId, Integer initialBalance, Integer finalBalance) {
}
