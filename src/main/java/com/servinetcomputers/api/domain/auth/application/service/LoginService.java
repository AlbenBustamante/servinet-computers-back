package com.servinetcomputers.api.domain.auth.application.service;

import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.security.JwtProvider;
import com.servinetcomputers.api.domain.auth.application.usecase.LoginUseCase;
import com.servinetcomputers.api.domain.auth.dto.AuthRequest;
import com.servinetcomputers.api.domain.auth.dto.AuthResponse;
import com.servinetcomputers.api.domain.user.persistence.JpaUserRepository;
import com.servinetcomputers.api.domain.user.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService implements LoginUseCase {
    private static final String BAD_LOGIN_MESSAGE = "La cuenta o la contraseña es inválida";

    private final JpaUserRepository jpaUserRepository;
    // private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    /**
     * Login for users and campuses.
     *
     * @param request the data to login.
     * @return the auth response.
     */
    @Transactional(readOnly = true)
    @Override
    public AuthResponse call(AuthRequest request) {
        // TODO: Update security config through authentication manager.
        final var user = jpaUserRepository.findByCodeAndEnabledTrue(request.code())
                .orElseThrow(() -> new BadRequestException(BAD_LOGIN_MESSAGE));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadRequestException(BAD_LOGIN_MESSAGE);
        }

        final var response = userMapper.toResponse(user);
        final var jwt = jwtProvider.create(response);

        return new AuthResponse(jwt);
    }
}
