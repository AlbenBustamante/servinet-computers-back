package com.servinetcomputers.api.domain.platform.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.domain.platform.application.usecase.CreatePlatformUseCase;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformResponse;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

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
    public PlatformResponse call(PlatformRequest param) {
        if (repository.existsByName(param.name())) {
            throw new BadRequestException("Ya existe la plataforma: " + param.name());
        }

        return repository.save(param);
    }
}
