package com.servinetcomputers.api.domain.user.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.user.application.usecase.DeleteUserUseCase;
import com.servinetcomputers.api.domain.user.application.usecase.GetUserUseCase;
import com.servinetcomputers.api.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class DeleteUserService implements DeleteUserUseCase {
    private final UserRepository repository;
    private final GetUserUseCase getUserUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public void call(Integer param) {
        final var user = getUserUseCase.call(param);
        user.setEnabled(false);

        repository.save(user);
    }
}
