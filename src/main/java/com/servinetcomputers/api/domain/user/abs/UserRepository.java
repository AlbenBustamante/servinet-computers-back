package com.servinetcomputers.api.domain.user.abs;

import com.servinetcomputers.api.domain.user.User;
import com.servinetcomputers.api.security.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The {@link User} repository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

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

}
