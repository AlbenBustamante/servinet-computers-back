package com.servinetcomputers.api.dto.request;

import com.servinetcomputers.api.util.enums.Role;

/**
 * The user dto model for requests.
 */
public record UserRequest(String name, String lastName, String email, String password, String repeatPassword,
                          Role role) {
    public boolean passwordsMatch() {
        return password.equals(repeatPassword);
    }
}
