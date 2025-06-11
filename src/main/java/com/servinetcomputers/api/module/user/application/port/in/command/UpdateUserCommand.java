package com.servinetcomputers.api.module.user.application.port.in.command;

/**
 * Comando para actualizar información básica de un usuario.
 *
 * @param name     Primer nombre.
 * @param lastName Apellido.
 */
public record UpdateUserCommand(String name, String lastName) {
}
