package com.servinetcomputers.api.domain.user.persistence;

import com.servinetcomputers.api.core.security.util.Role;
import com.servinetcomputers.api.domain.user.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The {@link User} repository.
 */
public interface JpaUserRepository extends JpaRepository<User, Integer> {
    /**
     * Find an existing user by the code.
     *
     * @param code the code to be searched.
     * @return an {@link Optional} of the user found.
     */
    Optional<User> findByCode(String code);

    /**
     * Find the last user by the role and creation date.
     *
     * @param role the role to be searched.
     * @return an {@link Optional} of the user found.
     */
    Optional<User> findFirstByRoleOrderByCreatedDateDesc(Role role);

    Optional<User> findByIdAndEnabledTrue(int id);
}
