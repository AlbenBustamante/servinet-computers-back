package com.servinetcomputers.api.module.auth.application.service;

import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.security.JwtProvider;
import com.servinetcomputers.api.module.auth.application.usecase.LoginUseCase;
import com.servinetcomputers.api.module.auth.dto.AuthRequest;
import com.servinetcomputers.api.module.auth.dto.AuthResponse;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserJpaRepository;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService implements LoginUseCase {
    private static final String BAD_LOGIN_MESSAGE = "La cuenta o la contraseña es inválida";

    private final UserJpaRepository userJpaRepository;
    // private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserPersistenceMapper userMapper;

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
        final var user = userJpaRepository.findByCodeAndEnabledTrue(request.code())
                .orElseThrow(() -> new BadRequestException(BAD_LOGIN_MESSAGE));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadRequestException(BAD_LOGIN_MESSAGE);
        }

        final var response = userMapper.toDto(user);
        final var jwt = jwtProvider.create(response);

        return new AuthResponse(jwt);
    }
}
