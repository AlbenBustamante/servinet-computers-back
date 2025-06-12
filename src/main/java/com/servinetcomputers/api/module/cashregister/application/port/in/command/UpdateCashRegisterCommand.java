package com.servinetcomputers.api.module.cashregister.application.port.in.command;

/**
 * Comando para actualizar una caja registradora.
 *
 * @param description Descripción.
 * @param disabled    {@code true} para inhabilitarla.
 */
public record UpdateCashRegisterCommand(
        String description,
        boolean disabled
) {
}
