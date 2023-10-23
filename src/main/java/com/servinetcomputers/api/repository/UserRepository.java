package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@link User} repository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
