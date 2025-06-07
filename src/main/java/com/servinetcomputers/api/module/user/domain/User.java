package com.servinetcomputers.api.module.user.domain;

import com.servinetcomputers.api.core.audit.infra.Auditable;
import com.servinetcomputers.api.core.util.enums.Role;

/**
 * Modelo de dominio para los usuarios.
 *
 * @param id       {@code ID} del usuario.
 * @param name     Primer nombre.
 * @param lastName Segundo nombre.
 * @param email    Correo electrónico.
 * @param password Contraseña sin encriptar.
 * @param code     Código único.
 * @param role     Rol asignado.
 * @param audit    Propiedades de auditoría.
 */
public record User(
        Integer id,
        String name,
        String lastName,
        String email,
        String password,
        String code,
        Role role,
        Auditable audit
) {
    public static User create(String name, String lastName, String email, String password, String code, Role role) {
        return new User(
                null,
                name.trim().toUpperCase(),
                lastName.trim().toUpperCase(),
                email.trim().toLowerCase(),
                password,
                code,
                role,
                null
        );
    }
}
