package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.module.user.application.usecase.DeleteUserUseCase;
import com.servinetcomputers.api.module.user.application.usecase.GetUserUseCase;
import com.servinetcomputers.api.module.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class DeleteUserService implements DeleteUserUseCase {
    private final UserRepository repository;
    private final GetUserUseCase getUserUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public void call(Integer userId) {
        final var user = getUserUseCase.call(userId);

        user.setName("ELIMINADO: " + user.getName());
        user.setLastName(user.getLastName() + " #" + user.getId());
        user.setEnabled(false);

        repository.save(user);
    }
}
