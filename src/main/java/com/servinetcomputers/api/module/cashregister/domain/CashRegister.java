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
    /**
     * Crea un nuevo {@link CashRegister} con la data proporcionada.
     *
     * @param numeral     numeral.
     * @param description descripción.
     * @param status      estado - puede ser nulo.
     * @return nuevo {@link CashRegister}.
     */
    public static CashRegister create(Integer numeral, String description, CashRegisterStatus status) {
        return new CashRegister(
                null,
                numeral,
                description,
                status != null ? status : CashRegisterStatus.AVAILABLE,
                null
        );
    }

    /**
     * Actualiza las propiedades para su posterior eliminación.
     *
     * @return {@link CashRegister} actualizado.
     */
    public CashRegister delete() {
        return new CashRegister(
                id,
                numeral,
                "ELIMINADO: ".concat(description),
                CashRegisterStatus.DISABLED,
                audit.delete()
        );
    }
}
