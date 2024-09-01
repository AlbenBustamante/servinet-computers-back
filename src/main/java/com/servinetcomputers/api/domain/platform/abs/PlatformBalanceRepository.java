package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.entity.PlatformBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The {@link PlatformBalance} repository.
 */
public interface PlatformBalanceRepository extends JpaRepository<PlatformBalance, Integer> {

    /**
     * Search all the platform balances between two dates.
     *
     * @return all platform balances found.
     */
    List<PlatformBalance> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<PlatformBalance> findByPlatformIdAndEnabledTrueAndCreatedDateBetween(int platformId, LocalDateTime startDate, LocalDateTime endDate);

}
