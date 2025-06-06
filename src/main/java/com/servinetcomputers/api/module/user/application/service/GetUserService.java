package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.user.application.usecase.GetUserUseCase;
import com.servinetcomputers.api.module.user.domain.repository.UserRepository;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetUserService implements GetUserUseCase {
    private final UserRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDto call(Integer param) {
        return repository.get(param)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + param));
    }
}
