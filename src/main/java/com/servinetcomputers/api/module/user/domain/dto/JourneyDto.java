package com.servinetcomputers.api.module.user.domain.dto;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;

import java.util.List;

/**
 * @param cashRegisterDetail  Detalles de la jornada trabajada.
 * @param discounts           Listado de gastos a descontar de la jornada.
 * @param totalOfDiscounts    Cantidad total a descontar.
 * @param totalOfTransactions Cantidad total de transacciones.
 * @param totalOfHours        Total de horas trabajadas durante la jornada.
 */
public record JourneyDto(
        CashRegisterDetailDto cashRegisterDetail,
        List<ExpenseDto> discounts,
        Integer totalOfDiscounts,
        Integer totalOfTransactions,
        String totalOfHours
) {
}
