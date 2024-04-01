package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
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

    List<Platform> findAllByEnabledTrue();

}
