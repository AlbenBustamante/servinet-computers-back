package com.servinetcomputers.api.module.platform.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.module.platform.application.usecase.CreatePlatformUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class CreatePlatformService implements CreatePlatformUseCase {
    private final PlatformRepository repository;

    /**
     * Create and persist a new platform.
     *
     * @param param the data to be saved.
     * @return the platform saved.
     */
    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public PlatformDto call(CreatePlatformDto param) {
        if (repository.existsByName(param.name())) {
            throw new BadRequestException("Ya existe la plataforma: " + param.name());
        }

        return repository.save(param);
    }
}
