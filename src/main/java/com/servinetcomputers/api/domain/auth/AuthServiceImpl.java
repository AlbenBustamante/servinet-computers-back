package com.servinetcomputers.api.domain.auth;

import com.servinetcomputers.api.domain.auth.abs.IAuthService;
import com.servinetcomputers.api.domain.auth.dto.AuthRequest;
import com.servinetcomputers.api.domain.auth.dto.AuthResponse;
import com.servinetcomputers.api.domain.user.abs.UserMapper;
import com.servinetcomputers.api.domain.user.abs.UserRepository;
import com.servinetcomputers.api.exception.BadRequestException;
import com.servinetcomputers.api.security.JwtProvider;
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
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public AuthResponse login(AuthRequest request) {
        final var user = userRepository.findByCode(request.code());

        if (user.isPresent() && passwordEncoder.matches(request.password(), user.get().getPassword())) {
            return new AuthResponse(jwtProvider.create(userMapper.toResponse(user.get())));
        }

        throw new BadRequestException("La cuenta o la contraseña es inválida");
    }

    @Override
    public boolean logout(String token) {
        return jwtProvider.delete(token.replace("Bearer ", ""));
    }

}
