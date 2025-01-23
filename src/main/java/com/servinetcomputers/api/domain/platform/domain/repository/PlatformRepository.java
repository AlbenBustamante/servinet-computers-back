package com.servinetcomputers.api.domain.platform.domain.repository;

import com.servinetcomputers.api.domain.platform.domain.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformResponse;

import java.util.List;
import java.util.Optional;

/**
 * The platform's repository.
 */
public interface PlatformRepository {
    boolean existsByName(String name);

    PlatformResponse save(PlatformRequest request);

    PlatformResponse save(PlatformResponse response);

    List<PlatformResponse> getAll();

    Optional<PlatformResponse> get(int id);
}
