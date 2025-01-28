package com.servinetcomputers.api.module.platform.persistence;

import com.servinetcomputers.api.module.platform.persistence.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The {@link Platform} repository.
 */
public interface JpaPlatformRepository extends JpaRepository<Platform, Integer> {
    boolean existsByName(String name);

    List<Platform> findAllByEnabledTrue();

    Optional<Platform> findByIdAndEnabledTrue(int id);
}
