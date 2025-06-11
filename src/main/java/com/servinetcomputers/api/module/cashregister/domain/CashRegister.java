package com.servinetcomputers.api.module.cashregister.domain;

import com.servinetcomputers.api.core.audit.infra.Auditable;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;

/**
 * Modelo de dominio para las cajas registradoras.
 *
 * @param id          {@code ID}.
 * @param numeral     Número de caja.
 * @param description Descripción.
 * @param status      Estado actual.
 * @param audit       {@link Auditable}.
 */
public record CashRegister(
        Integer id,
        Integer numeral,
        String description,
        CashRegisterStatus status,
        Auditable audit
) {
}
