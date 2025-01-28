package com.servinetcomputers.api.module.auth.application.service;

import com.servinetcomputers.api.core.security.JwtProvider;
import com.servinetcomputers.api.module.auth.application.usecase.LogoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogoutService implements LogoutUseCase {
    private final JwtProvider jwtProvider;

    /**
     * Logout for any token.
     *
     * @param token the token.
     * @return {@code true} if the token was removed.
     */
    @Override
    public Boolean call(String token) {
        final var jwt = token.replace("Bearer ", "");
        return jwtProvider.delete(jwt);
    }
}
