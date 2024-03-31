package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.model.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.model.dto.PlatformResponse;

import java.util.List;

/**
 * The platform's uses case.
 */
public interface IPlatformService {

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
