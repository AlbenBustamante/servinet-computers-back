package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.request.PlatformRequest;
import com.servinetcomputers.api.dto.response.PlatformResponse;

import java.util.Optional;

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
     * Update an existing and available platform.
     *
     * @param platformId the ID to be searched.
     * @param request    the data to be updated.
     * @return an {@link Optional} of the platform updated.
     */
    Optional<PlatformResponse> update(int platformId, PlatformRequest request);

    /**
     * Disable an existing and available platform.
     *
     * @param platformId the ID to be searched.
     * @return {@code true} if the platform was disabled.
     */
    boolean delete(int platformId);

}
