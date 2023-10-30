package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.request.CampusRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;

import java.util.List;
import java.util.Optional;

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
    CampusResponse create(CampusRequest request);

    /**
     * Search a campus by the ID.
     *
     * @param campusId the ID to be searched.
     * @return an {@link Optional} of the available campus found.
     */
    Optional<CampusResponse> get(int campusId);

    /**
     * Get all the existing and available campuses by the user ID.
     *
     * @param userId the owner ID for the searching.
     * @return an {@link Optional} of the campuses found.
     */
    Optional<List<CampusResponse>> getAllByUserId(int userId);

    /**
     * Update an existing and available campus.
     *
     * @param campusId the ID to be searched.
     * @param request  the data to be updated.
     * @return and {@link Optional} of the campus updated.
     */
    Optional<CampusResponse> update(int campusId, CampusRequest request);

    /**
     * Disable an existing and available campus.
     *
     * @param campusId the ID to be searched.
     * @return {@code true} if the campus was disabled.
     */
    boolean delete(int campusId);

}
