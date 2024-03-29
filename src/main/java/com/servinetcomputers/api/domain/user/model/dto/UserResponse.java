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
    private final String name, lastName, code;
    private final Role role;

    public UserResponse(int id, String name, String lastName, String code, Role role,
                        boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        super(id, enabled, createdDate, modifiedDate);
        this.name = name;
        this.lastName = lastName;
        this.code = code;
        this.role = role;
    }
}
