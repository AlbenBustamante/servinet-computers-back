package com.servinetcomputers.api.core.util.enums;

/**
 * Estado actual del movimiento de caja registradora.
 */
public enum CashRegisterDetailStatus {
    /**
     * Trabajando.
     */
    WORKING('W'),
    /**
     * Descansando.
     */
    RESTING('R'),
    /**
     * Finalizado.
     */
    CLOSED('C');

    /**
     * Inicial del estado.
     */
    private final Character status;

    CashRegisterDetailStatus(final Character status) {
        this.status = status;
    }

    public final Character getStatus() {
        return status;
    }
}
