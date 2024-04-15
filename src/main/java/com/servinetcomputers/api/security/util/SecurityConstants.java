package com.servinetcomputers.api.security.util;

import com.servinetcomputers.api.domain.user.dto.UserResponse;

/**
 * The security constants.
 */
public final class SecurityConstants {
    public static final String[] WHITE_LIST = {"/auth", "/auth/**", "/swagger-ui.html", "/swagger-ui", "/api-docs", "/swagger-ui/**", "/api-docs/**", "/test/pong"};
    public static final String CASHIER_AUTHORITY = "ROLE_CASHIER";
    public static final String SUPERVISOR_AUTHORITY = "ROLE_SUPERVISOR";
    public static final String ADMIN_AUTHORITY = "ROLE_ADMIN";

    public static String getAuthority(final UserResponse user) {
        return "ROLE_" + user.getRole();
    }

    private SecurityConstants() {
    }
}
