package com.servinetcomputers.api.domain.platform.domain.repository;

import com.servinetcomputers.api.domain.platform.domain.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.PortalPlatformDto;

import java.util.List;

/**
 * The platform's repository.
 */
public interface PlatformRepository {
    /**
     * Create and persist a new platform.
     *
     * @param request the data to be saved.
     * @return the platform saved.
     */
    PlatformResponse create(PlatformRequest request);

    /**
     * Get all the available platforms.
     *
     * @return the platforms.
     */
    List<PlatformResponse> getAll();

    /**
     * Get all the available portal platforms.
     *
     * @return the platforms.
     */
    List<PortalPlatformDto> loadPortalPlatforms();

    /**
     * Update an existing and available platform.
     *
     * @param platformId the ID to be searched.
     * @param request    the data to be updated.
     * @return the platform updated.
     */
    PlatformResponse update(int platformId, PlatformRequest request);

    /**
     * Disable an existing and available platform.
     *
     * @param platformId the ID to be searched.
     * @return {@code true} if the platform was disabled.
     */
    boolean delete(int platformId);
}
