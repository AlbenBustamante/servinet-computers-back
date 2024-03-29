package com.servinetcomputers.api.domain.user.abs;

import com.servinetcomputers.api.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The {@link User} repository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * @param code the code to be searched.
     * @return {@code true} if the code already exists.
     */
    boolean existsByCode(String code);

    /**
     * Find an existing user by the code.
     *
     * @param code the code to be searched.
     * @return an {@link Optional} of the user found.
     */
    Optional<User> findByCode(String code);

}
