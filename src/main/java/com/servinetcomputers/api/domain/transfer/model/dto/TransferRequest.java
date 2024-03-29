package com.servinetcomputers.api.domain.transfer.model.dto;

import java.math.BigDecimal;

/**
 * The transfer dto model for requests.
 */
public record TransferRequest(String platformName, int campusId, BigDecimal value, int amount) {
}
