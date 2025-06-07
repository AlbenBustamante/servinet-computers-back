package com.servinetcomputers.api.module.user.application.port.in.command;

import com.servinetcomputers.api.core.util.enums.Role;

/**
 * Comando para registrar a un nuevo usuario.
 *
 * @param name     Primer nombre.
 * @param lastName Apellido.
 * @param email    Correo electrónico.
 * @param role     Rol por asignar.
 */
public record CreateUserCommand(
        String name,
        String lastName,
        String email,
        Role role
) {
}
