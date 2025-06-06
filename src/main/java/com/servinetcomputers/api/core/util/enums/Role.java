package com.servinetcomputers.api.core.util.enums;

import lombok.Getter;

/**
 * Roles disponibles para un usuario.
 */
@Getter
public enum Role {
    /**
     * Cajero.
     */
    CASHIER("C"),
    /**
     * Supervisor.
     */
    SUPERVISOR("S"),
    /**
     * Administrador.
     */
    ADMIN("A");

    Role(final String role) {
        this.role = role;
    }

    /**
     * Primer carácter del rol.
     */
    private final String role;
}
