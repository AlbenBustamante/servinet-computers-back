package com.servinetcomputers.api.core.util.enums;

import lombok.Getter;

/**
 * The Role types for users.
 */
@Getter
public enum Role {
    CASHIER("C"),
    SUPERVISOR("S"),
    ADMIN("A");

    Role(final String role) {
        this.role = role;
    }

    private final String role;
}
