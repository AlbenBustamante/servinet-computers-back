package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.request.UserRequest;
import com.servinetcomputers.api.dto.response.UserResponse;

import java.util.Optional;

/**
 * The user's uses case.
 */
public interface IUserService {

    /**
     * Create and persist a new user.
     *
     * @param request the data to save.
     * @return the user saved.
     */
    UserResponse create(UserRequest request);

    /**
     * Update an existing and available user.
     *
     * @param userId  the ID of the user to be updated.
     * @param request the data to update.
     * @return an {@link Optional} of the user updated.
     */
    Optional<UserResponse> update(int userId, UserRequest request);

    /**
     * Disable an existing and available user.
     *
     * @param userId the ID to be searched.
     * @return {@code true} if the user was disabled.
     */
    boolean delete(int userId);

}
