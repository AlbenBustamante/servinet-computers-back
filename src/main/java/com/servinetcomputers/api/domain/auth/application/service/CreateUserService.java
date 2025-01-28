package com.servinetcomputers.api.domain.auth.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.domain.auth.application.usecase.CreateUserUseCase;
import com.servinetcomputers.api.domain.user.domain.dto.UserRequest;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import com.servinetcomputers.api.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateUserService implements CreateUserUseCase {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public UserResponse call(UserRequest param) {
        if (!param.passwordsMatch()) {
            throw new BadRequestException("Las contraseñas no coinciden");
        }

        param.setPassword(passwordEncoder.encode(param.getPassword()));

        final var lastUser = repository.getLastByRole(param.getRole());
        final var role = param.getRole().getRole().toLowerCase();

        final var code = lastUser.map(user -> {
            final var numberCode = user.getCode().split(role)[1];
            return Integer.parseInt(numberCode);
        }).orElse(0);

        param.setCode(role + (code + 1));

        return repository.save(param);
    }
}
