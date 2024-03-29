package com.servinetcomputers.api.dto.response;

import com.servinetcomputers.api.util.enums.Role;
import lombok.Getter;

/**
 * The user dto model for responses.
 */
@Getter
public class UserResponse extends ModelResponse {
    private final String name, lastName, email;
    private final Role role;

    public UserResponse(int id, String name, String lastName, String email, Role role,
                        boolean isAvailable, String createdAt, String updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
