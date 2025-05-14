package com.servinetcomputers.api.module.user.persistence;

import com.servinetcomputers.api.core.util.enums.Role;
import com.servinetcomputers.api.module.user.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * The {@link User} repository.
 */
public interface JpaUserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmailAndEnabledTrue(String email);

    @Query("SELECT u.email FROM User u " +
            "WHERE u.code = :code " +
            "AND u.enabled = true")
    Optional<String> findEmailByCodeAndEnabledTrue(String code);

    /**
     * Find an existing enabled user by the code.
     *
     * @param code the code to be searched.
     * @return an {@link Optional} of the user found.
     */
    Optional<User> findByCodeAndEnabledTrue(String code);

    /**
     * Find the last user by the role and creation date.
     *
     * @param role the role to be searched.
     * @return an {@link Optional} of the user found.
     */
    Optional<User> findFirstByRoleOrderByCreatedDateDesc(Role role);

    Optional<User> findByIdAndEnabledTrue(int id);
}
