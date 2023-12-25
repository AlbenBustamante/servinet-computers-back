package com.servinetcomputers.api.util.constants;

import com.servinetcomputers.api.dto.response.UserResponse;

/**
 * The security constants.
 */
public final class SecurityConstants {
    public static final String[] WHITE_LIST = {"/auth", "/auth/**", "/swagger-ui.html", "/swagger-ui", "/api-docs", "/swagger-ui/**", "/v3/api-docs/**"};
    public static final String CAMPUS_AUTHORITY = "ROLE_CAMPUS";
    public static final String USER_AUTHORITY = "ROLE_USER";
    public static final String ADMIN_AUTHORITY = "ROLE_ADMIN";

    public static String getAuthority(final UserResponse user) {
        return "ROLE_" + user.getRole();
    }

    private SecurityConstants() {
    }
}
