package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.AuthRequest;
import com.servinetcomputers.api.dto.response.AuthResponse;
import com.servinetcomputers.api.exception.AuthenticationException;
import com.servinetcomputers.api.mapper.UserMapper;
import com.servinetcomputers.api.repository.UserRepository;
import com.servinetcomputers.api.security.JwtProvider;
import com.servinetcomputers.api.service.IAuthService;
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
    private final CampusRepository campusRepository;
    private final UserMapper userMapper;
    private final CampusMapper campusMapper;

    @Override
    public AuthResponse login(AuthRequest request) {
        final var user = userRepository.findByEmail(request.username());

        if (user.isPresent() && passwordEncoder.matches(request.password(), user.get().getPassword())) {
            return new AuthResponse(jwtProvider.create(userMapper.toResponse(user.get())));
        }

        final var campus = campusRepository.findByTerminal(request.username());

        if (campus.isPresent() && passwordEncoder.matches(request.password(), campus.get().getPassword())) {
            return new AuthResponse(jwtProvider.create(campusMapper.toResponse(campus.get())));
        }

        throw new AuthenticationException("Something went wrong...", "BAD CREDENTIALS");
    }

    @Override
    public boolean logout(String token) {
        return jwtProvider.delete(token.replace("Bearer ", ""));
    }

}
