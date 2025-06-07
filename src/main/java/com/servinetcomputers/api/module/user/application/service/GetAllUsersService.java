package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.module.user.application.usecase.GetAllUsersUseCase;
import com.servinetcomputers.api.module.user.domain.repository.UserRepository;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class GetAllUsersService implements GetAllUsersUseCase {
    private final UserRepository repository;

    @Transactional(readOnly = true)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public List<UserDto> call() {
        return repository.getAll();
    }
}
