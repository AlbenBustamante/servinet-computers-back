package com.servinetcomputers.api.module.user.domain.repository;

import com.servinetcomputers.api.core.util.enums.Role;
import com.servinetcomputers.api.module.user.domain.dto.CreateUserDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * The user's repository.
 */
public interface UserRepository {
    UserDto save(CreateUserDto request);

    UserDto save(UserDto response);

    boolean existsByEmail(String email);

    Optional<UserDto> getLastByRole(Role role);

    Optional<UserDto> getByCode(String code);

    List<UserDto> getAll();

    /**
     * Get an existing and available user by the user ID.
     *
     * @param userId the ID to search.
     * @return the user found.
     */
    Optional<UserDto> get(int userId);
}
