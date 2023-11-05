package com.servinetcomputers.api.dto.response;

import com.servinetcomputers.api.util.enums.Role;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The user dto model for responses.
 */
@Getter
public class UserResponse extends ModelResponse {
    private final String name, lastName, email;
    private final Role role;
    private final List<CampusResponse> campuses;

    public UserResponse(int id, String name, String lastName, String email, Role role, List<CampusResponse> campuses,
                        boolean isAvailable, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, isAvailable, createdAt, updatedAt);
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.campuses = campuses;
    }
}
