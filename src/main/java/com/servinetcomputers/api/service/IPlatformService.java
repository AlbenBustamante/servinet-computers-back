package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.request.PlatformRequest;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.PlatformResponse;

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
    PageResponse<PlatformResponse> create(PlatformRequest request);

    /**
     * Update an existing and available platform.
     *
     * @param platformId the ID to be searched.
     * @param request    the data to be updated.
     * @return the platform updated.
     */
    PageResponse<PlatformResponse> update(int platformId, PlatformRequest request);

    /**
     * Disable an existing and available platform.
     *
     * @param platformId the ID to be searched.
     * @return {@code true} if the platform was disabled.
     */
    boolean delete(int platformId);

}
