package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.request.CampusRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.dto.response.PageResponse;

/**
 * The campus' uses case.
 */
public interface ICampusService {

    /**
     * Create and persist a new campus.
     *
     * @param request the data to save.
     * @return the campus saved.
     */
    PageResponse<CampusResponse> create(CampusRequest request);

    /**
     * Search a campus by the ID.
     *
     * @param campusId the ID to be searched.
     * @return the available campus found.
     */
    PageResponse<CampusResponse> get(int campusId);

    /**
     * Get all the existing and available campuses by the user ID.
     *
     * @param userId the owner ID for the searching.
     * @return the campuses found.
     */
    PageResponse<CampusResponse> getAllByUserId(int userId);

    /**
     * Update an existing and available campus.
     *
     * @param campusId the ID to be searched.
     * @param request  the data to be updated.
     * @return the campus updated.
     */
    PageResponse<CampusResponse> update(int campusId, CampusRequest request);

    /**
     * Disable an existing and available campus.
     *
     * @param campusId the ID to be searched.
     * @return {@code true} if the campus was disabled.
     */
    boolean delete(int campusId);

    /**
     * Add a platform to the campus' collection.
     *
     * @param campusId     the campus ID.
     * @param platformName the platform name to be added.
     * @return the updated campus.
     */
    PageResponse<CampusResponse> addPlatform(int campusId, String platformName);

    /**
     * Remove a platform from the campus' collection.
     *
     * @param campusId     the campus ID.
     * @param platformName the platform name to be removed.
     * @return the updated campus.
     */
    PageResponse<CampusResponse> removePlatform(int campusId, String platformName);

}
