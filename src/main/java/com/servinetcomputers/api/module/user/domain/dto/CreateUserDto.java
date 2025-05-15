package com.servinetcomputers.api.module.user.domain.dto;

import com.servinetcomputers.api.core.util.enums.Role;
import lombok.Getter;
import lombok.Setter;

/**
 * The user dto model for requests.
 */
@Getter
@Setter
public class CreateUserDto {
    private String password, code, name, lastName, email;
    private Role role;
}
