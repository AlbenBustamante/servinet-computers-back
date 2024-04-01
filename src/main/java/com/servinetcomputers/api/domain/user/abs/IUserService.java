package com.servinetcomputers.api.domain.user.abs;

import com.servinetcomputers.api.domain.user.model.dto.UserRequest;
import com.servinetcomputers.api.domain.user.model.dto.UserResponse;

import java.util.List;

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

    List<UserResponse> getAll();

    /**
     * Get an existing and available user by the user ID.
     *
     * @param userId the ID to search.
     * @return the user found.
     */
    UserResponse get(int userId);

    /**
     * Update an existing and available user.
     *
     * @param userId  the ID of the user to be updated.
     * @param request the data to update.
     * @return the user updated.
     */
    UserResponse update(int userId, UserRequest request);

    /**
     * Disable an existing and available user.
     *
     * @param userId the ID to be searched.
     * @return {@code true} if the user was disabled.
     */
    boolean delete(int userId);

}
