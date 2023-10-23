package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The {@link Platform} repository.
 */
public interface PlatformRepository extends JpaRepository<Platform, Integer> {

    /**
     * Search a {@link Platform} by the name.
     *
     * @param name the name to be searched.
     * @return an {@link Optional} of the platform found.
     */
    Optional<Platform> findByName(String name);

}
