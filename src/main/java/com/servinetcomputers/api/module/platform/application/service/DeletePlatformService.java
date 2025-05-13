package com.servinetcomputers.api.module.platform.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.platform.application.usecase.DeletePlatformUseCase;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class DeletePlatformService implements DeletePlatformUseCase {
    private final PlatformRepository repository;

    /**
     * Disable an existing and available platform.
     *
     * @param platformId the ID to be searched.
     */
    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public void call(Integer platformId) {
        final var platform = repository.get(platformId)
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: " + platformId));

        platform.setName("ELIMINADO: " + platform.getName() + " #" + platformId);
        platform.setEnabled(false);
        repository.save(platform);
    }
}
