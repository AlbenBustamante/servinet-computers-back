package com.servinetcomputers.api.module.user.domain.dto;

import java.util.List;

/**
 * @param totalOfHours        Cantidad total de horas trabajadas en el mes.
 * @param totalOfDiscounts    Cantidad total de dinero a descontar del mes.
 * @param totalOfTransactions Cantidad total de transacciones del mes.
 * @param journeys            Jornadas trabajadas en el mes.
 * @see JourneyDto
 */
public record JourneyDetailDto(
        String totalOfHours,
        Integer totalOfDiscounts,
        Integer totalOfTransactions,
        List<JourneyDto> journeys
) {
}
