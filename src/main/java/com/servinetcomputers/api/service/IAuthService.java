package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.request.AuthRequest;
import com.servinetcomputers.api.dto.response.AuthResponse;

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
