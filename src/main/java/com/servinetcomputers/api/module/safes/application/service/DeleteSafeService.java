package com.servinetcomputers.api.module.safes.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.safes.application.usecase.detail.DeleteSafeUseCase;
import com.servinetcomputers.api.module.safes.domain.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class DeleteSafeService implements DeleteSafeUseCase {
    private final SafeRepository safeRepository;

    @Secured(value = ADMIN_AUTHORITY)
    @Transactional(rollbackFor = AppException.class)
    @Override
    public void call(Integer safeId) {
        final var safe = safeRepository.get(safeId)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja con ID: " + safeId));

        safe.setEnabled(false);

        safeRepository.save(safe);
    }
}
