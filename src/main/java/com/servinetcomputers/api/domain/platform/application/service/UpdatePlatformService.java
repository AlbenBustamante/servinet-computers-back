package com.servinetcomputers.api.domain.platform.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.platform.application.usecase.UpdatePlatformUseCase;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.UpdatePlatformDto;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class UpdatePlatformService implements UpdatePlatformUseCase {
    private final PlatformRepository repository;

    /**
     * Update an existing and available platform.
     *
     * @param param the info to update the platform.
     * @return the platform updated.
     */
    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public PlatformResponse call(UpdatePlatformDto param) {
        final var platform = repository.get(param.platformId())
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: " + param.platformId()));

        platform.setName(param.name() != null ? param.name() : platform.getName());

        return repository.save(platform);
    }
}
