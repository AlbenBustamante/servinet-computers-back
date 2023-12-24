package com.servinetcomputers.api.util.constants;

/**
 * The security constants.
 */
public final class SecurityConstants {
    public static final String[] WHITE_LIST = {"/auth", "/auth/**", "/swagger-ui.html", "/swagger-ui", "/api-docs", "/swagger-ui/**", "/v3/ai-docs/**" };

    private SecurityConstants() {
    }
}
