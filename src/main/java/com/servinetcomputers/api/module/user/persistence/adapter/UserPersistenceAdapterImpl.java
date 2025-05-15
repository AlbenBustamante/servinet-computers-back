package com.servinetcomputers.api.module.user.persistence.adapter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.user.domain.adapter.UserPersistenceAdapter;
import com.servinetcomputers.api.module.user.persistence.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapterImpl implements UserPersistenceAdapter {
    private final JpaUserRepository repository;

    @Override
    public void savePassword(String userCode, String password) {
        final var user = repository.findByCodeAndEnabledTrue(userCode)
                .orElseThrow(() -> new NotFoundException("No se encontró al usuario solicitado: " + userCode));

        user.setPassword(password);
    }

    @Override
    public boolean existsByCode(String userCode) {
        return repository.existsByCodeAndEnabledTrue(userCode);
    }
}
