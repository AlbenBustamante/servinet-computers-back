package com.servinetcomputers.api.domain.user.domain.dto;

import com.servinetcomputers.api.core.util.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * The user dto model for requests.
 */
@RequiredArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String password, code;
    private final String name, lastName, repeatPassword;
    private final Role role;

    public boolean passwordsMatch() {
        return password.equals(repeatPassword);
    }
}
