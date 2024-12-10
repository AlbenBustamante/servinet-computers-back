package com.servinetcomputers.api.domain.user.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.core.security.util.Role;
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
                        boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate,
                        String createdBy, String modifiedBy) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.name = name;
        this.lastName = lastName;
        this.code = code;
        this.role = role;
    }
}
