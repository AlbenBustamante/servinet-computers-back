package com.servinetcomputers.api.core.util.enums;

import lombok.Getter;

/**
 * Estado actual de una Caja Registradora.
 */
@Getter
public enum CashRegisterStatus {
    /**
     * Disponible.
     */
    AVAILABLE('A'),
    /**
     * Ocupada.
     */
    OCCUPIED('O'),
    /**
     * Deshabilitada.
     */
    DISABLED('D');

    CashRegisterStatus(final Character status) {
        this.status = status;
    }

    /**
     * Inicial del estado.
     */
    private final Character status;
}
