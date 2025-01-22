package com.servinetcomputers.api.domain.user.application.service;

import com.servinetcomputers.api.domain.user.application.usecase.GetUserUseCase;
import com.servinetcomputers.api.domain.user.application.usecase.UpdateUserUseCase;
import com.servinetcomputers.api.domain.user.domain.dto.UpdateUserDto;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import com.servinetcomputers.api.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateUserService implements UpdateUserUseCase {
    private final UserRepository repository;
    private final GetUserUseCase getUserUseCase;

    @Transactional(readOnly = true)
    @Override
    public UserResponse call(UpdateUserDto param) {
        final var user = getUserUseCase.call(param.userId());
        final var request = param.request();

        user.setName(request.getName() == null ? user.getName() : request.getName());
        user.setLastName(request.getLastName() == null ? user.getLastName() : request.getLastName());

        return repository.save(user);
    }
}
