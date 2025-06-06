package com.servinetcomputers.api.module.user.infrastructure.in.rest.dto;

import java.util.List;

/**
 * @param totalOfHours     Cantidad total de horas trabajadas en el mes.
 * @param totalOfDiscounts Cantidad total de dinero a descontar del mes.
 * @param journeys         Jornadas trabajadas en el mes.
 * @see JourneyDto
 */
public record JourneyDetailDto(
        String totalOfHours,
        Integer totalOfDiscounts,
        List<JourneyDto> journeys
) {
}
