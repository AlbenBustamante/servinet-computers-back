package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The {@link Platform} repository.
 */
public interface PlatformRepository extends JpaRepository<Platform, Integer> {

    boolean existsByName(String name);

    List<Platform> findAllByEnabledTrue();

}
