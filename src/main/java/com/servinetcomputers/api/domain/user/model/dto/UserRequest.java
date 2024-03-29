package com.servinetcomputers.api.domain.user.model.dto;

import com.servinetcomputers.api.security.util.Role;

/**
 * The user dto model for requests.
 */
public record UserRequest(String name, String lastName, String email, String password, String repeatPassword,
                          Role role) {
    public boolean passwordsMatch() {
        return password.equals(repeatPassword);
    }
}
