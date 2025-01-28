package com.servinetcomputers.api.module.platform.application.service;

import com.servinetcomputers.api.module.platform.application.usecase.GetAllPlatformsUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformResponse;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllPlatformsService implements GetAllPlatformsUseCase {
    private final PlatformRepository repository;

    /**
     * Get all the available platforms.
     *
     * @return the platforms.
     */
    @Transactional(readOnly = true)
    @Override
    public List<PlatformResponse> call() {
        return repository.getAll();
    }
}
