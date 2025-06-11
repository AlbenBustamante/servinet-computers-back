package com.servinetcomputers.api.module.expense.domain;

import com.servinetcomputers.api.core.audit.infra.Auditable;
import com.servinetcomputers.api.module.cashregister.domain.CashRegisterDetail;

/**
 * Modelo de dominio para gastos.
 *
 * @param id                 {@code ID}.
 * @param description        Descripción.
 * @param value              Valor.
 * @param discount           Descontar de nómina.
 * @param administrative     Administrativo.
 * @param cashRegisterDetail Movimientos de caja registradora.
 * @param audit              {@link Auditable}.
 * @see CashRegisterDetail
 */
public record Expense(
        Integer id,
        String description,
        Integer value,
        Boolean discount,
        Boolean administrative,
        CashRegisterDetail cashRegisterDetail,
        Auditable audit
) {
}
