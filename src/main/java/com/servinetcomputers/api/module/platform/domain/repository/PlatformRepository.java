package com.servinetcomputers.api.module.platform.domain.repository;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformDto;

import java.util.List;
import java.util.Optional;

/**
 * The platform's repository.
 */
public interface PlatformRepository {
    boolean existsByName(String name);

    PlatformDto save(CreatePlatformDto request);

    PlatformDto save(PlatformDto response);

    List<PlatformDto> getAll();

    Optional<PlatformDto> get(int id);
}
