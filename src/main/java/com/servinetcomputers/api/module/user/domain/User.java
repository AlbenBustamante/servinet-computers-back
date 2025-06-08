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

    /**
     * Copia las propiedades de un usuario y las sobreescribe si no recibe datos nulos.
     *
     * @param name     Primer nombre.
     * @param lastName Apellido.
     * @return Nuevo {@link User}.
     */
    public User copyWith(String name, String lastName) {
        return new User(
                id,
                name != null ? name : this.name,
                lastName != null ? lastName : this.lastName,
                email,
                password,
                code,
                role,
                audit
        );
    }

    /**
     * Ajusta las propiedades como un usuario eliminado.
     *
     * @return el nuevo {@link User}.
     */
    public User delete() {
        return new User(
                id,
                "ELIMINADO: " + name,
                lastName,
                email,
                password,
                code,
                role,
                audit.delete()
        );
    }
}
