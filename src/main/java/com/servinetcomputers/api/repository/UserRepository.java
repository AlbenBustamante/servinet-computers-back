package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@link User} repository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * @param email the email to be searched.
     * @return {@code true} if the email already exists.
     */
    boolean existsByEmail(String email);

}
