package com.servinetcomputers.api.domain.auth.abs;

import com.servinetcomputers.api.domain.auth.dto.AuthRequest;
import com.servinetcomputers.api.domain.auth.dto.AuthResponse;

/**
 * The authentication uses case.
 */
public interface IAuthService {

    /**
     * Login for users and campuses.
     *
     * @param request the data to login.
     * @return the auth response.
     */
    AuthResponse login(AuthRequest request);

    /**
     * Logout for any token.
     *
     * @param token the token.
     * @return {@code true} if the token was removed.
     */
    boolean logout(String token);

}
