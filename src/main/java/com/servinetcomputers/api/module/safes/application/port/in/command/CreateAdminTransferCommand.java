package com.servinetcomputers.api.module.safes.application.port.in.command;

/**
 * Comando para crear una nueva transferencia desde la administración de una caja fuerte.
 *
 * @param amount       La cantidad de billetes o monedas.
 * @param denomination La denominación del billete o moneda.
 * @param add          {@code true} si el dinero ingresa a la caja fuerte, {@code false} saldrá de la caja fuerte.
 */
public record CreateAdminTransferCommand(
        Integer amount,
        Integer denomination,
        Boolean add
) {
}
