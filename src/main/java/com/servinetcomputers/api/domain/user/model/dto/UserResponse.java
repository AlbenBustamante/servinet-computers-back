package com.servinetcomputers.api.domain.user.model.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.security.util.Role;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The user dto model for responses.
 */
@Getter
public class UserResponse extends ModelResponse {
    private final String name, lastName, email;
    private final Role role;

    public UserResponse(int id, String name, String lastName, String email, Role role,
                        boolean isAvailable, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
