package com.servinetcomputers.api.dto.request;

import java.math.BigDecimal;

/**
 * The transfer dto model for requests.
 */
public record TransferRequest(String platformName, int campusId, BigDecimal value) {
}
