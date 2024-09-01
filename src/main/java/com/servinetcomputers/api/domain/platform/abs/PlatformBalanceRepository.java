package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.entity.PlatformBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The {@link PlatformBalance} repository.
 */
public interface PlatformBalanceRepository extends JpaRepository<PlatformBalance, Integer> {

    Optional<PlatformBalance> findByPlatformIdAndEnabledTrueAndCreatedDateBetween(int platformId, LocalDateTime startDate, LocalDateTime endDate);

}
