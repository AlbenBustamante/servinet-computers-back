package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.module.user.application.usecase.GetUserUseCase;
import com.servinetcomputers.api.module.user.application.usecase.UpdateUserUseCase;
import com.servinetcomputers.api.module.user.domain.dto.UpdateUserDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;
import com.servinetcomputers.api.module.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateUserService implements UpdateUserUseCase {
    private final UserRepository repository;
    private final GetUserUseCase getUserUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public UserDto call(Integer id, UpdateUserDto dto) {
        final var user = getUserUseCase.call(id);

        user.setName(dto.name() == null ? user.getName() : dto.name());
        user.setLastName(dto.lastName() == null ? user.getLastName() : dto.lastName());

        return repository.save(user);
    }
}
