package com.servinetcomputers.api.domain.user.domain.dto;

import com.servinetcomputers.api.core.security.util.Role;
import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The user dto model for responses.
 */
@Getter
@Setter
public class UserResponse extends ModelResponse {
    private String name, lastName;
    private final String code;
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
