package com.servinetcomputers.api.module.user.domain.repository;

import com.servinetcomputers.api.core.util.enums.Role;
import com.servinetcomputers.api.module.user.domain.dto.UserRequest;
import com.servinetcomputers.api.module.user.domain.dto.UserResponse;

import java.util.List;
import java.util.Optional;

/**
 * The user's repository.
 */
public interface UserRepository {
    UserResponse save(UserRequest request);

    UserResponse save(UserResponse response);

    Optional<UserResponse> getLastByRole(Role role);

    Optional<UserResponse> getByCode(String code);

    List<UserResponse> getAll();

    /**
     * Get an existing and available user by the user ID.
     *
     * @param userId the ID to search.
     * @return the user found.
     */
    Optional<UserResponse> get(int userId);
}
