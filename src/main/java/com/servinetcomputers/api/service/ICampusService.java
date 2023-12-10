package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.request.CampusRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.dto.response.PageResponse;

import java.util.List;

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
     * Receives a names list to add, and the platforms that are not included will be removed.
     *
     * @param campusId      the campus ID.
     * @param platformNames the platforms list (only the names)
     * @return the updated campus.
     */
    PageResponse<CampusResponse> updatePlatforms(int campusId, List<String> platformNames);

}
