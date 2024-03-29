package com.servinetcomputers.api.util.constants;

import com.servinetcomputers.api.dto.response.UserResponse;

/**
 * The security constants.
 */
public final class SecurityConstants {
    public static final String[] WHITE_LIST = {"/auth", "/auth/**", "/swagger-ui.html", "/swagger-ui", "/api-docs", "/swagger-ui/**", "/api-docs/**", "/test/pong"};
    public static final String USER_AUTHORITY = "ROLE_USER";
    public static final String ADMIN_AUTHORITY = "ROLE_ADMIN";

    public static String getAuthority(final UserResponse user) {
        return "ROLE_" + user.getRole();
    }

    private SecurityConstants() {
    }
}
