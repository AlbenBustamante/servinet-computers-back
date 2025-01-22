package com.servinetcomputers.api.domain.auth;

import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.security.JwtProvider;
import com.servinetcomputers.api.domain.auth.abs.IAuthService;
import com.servinetcomputers.api.domain.auth.dto.AuthRequest;
import com.servinetcomputers.api.domain.auth.dto.AuthResponse;
import com.servinetcomputers.api.domain.user.persistence.JpaUserRepository;
import com.servinetcomputers.api.domain.user.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The authentication service implementation.
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public AuthResponse login(AuthRequest request) {
        final var user = jpaUserRepository.findByCode(request.code());

        if (user.isEmpty()
                || user.get().getEnabled().equals(Boolean.FALSE)
                || !passwordEncoder.matches(request.password(), user.get().getPassword())
        ) {
            throw new BadRequestException("La cuenta o la contraseña es inválida");
        }

        return new AuthResponse(jwtProvider.create(userMapper.toResponse(user.get())));
    }

    @Override
    public boolean logout(String token) {
        return jwtProvider.delete(token.replace("Bearer ", ""));
    }

}
