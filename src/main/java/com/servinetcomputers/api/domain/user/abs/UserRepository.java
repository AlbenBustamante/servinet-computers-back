package com.servinetcomputers.api.domain.user.abs;

import com.servinetcomputers.api.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The {@link User} repository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * @param email the email to be searched.
     * @return {@code true} if the email already exists.
     */
    boolean existsByEmail(String email);

    /**
     * Find an existing user by the email.
     *
     * @param email the email to be searched.
     * @return an {@link Optional} of the user found.
     */
    Optional<User> findByEmail(String email);

}
