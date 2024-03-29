package com.servinetcomputers.api.domain.balance.model.dto;

import java.math.BigDecimal;

/**
 * The balance's dto model for requests.
 */
public record BalanceRequest(String name, int campusId, BigDecimal initialBalance, BigDecimal finalBalance) {
}
