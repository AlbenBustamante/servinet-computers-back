package com.servinetcomputers.api.domain.platform.dto;

/**
 * The balance's dto model for requests.
 */
public record PlatformBalanceRequest(int platformId, Integer initialBalance, Integer finalBalance) {
}
