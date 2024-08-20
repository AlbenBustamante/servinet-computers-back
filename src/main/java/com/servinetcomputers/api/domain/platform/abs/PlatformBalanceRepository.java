package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.entity.PlatformBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

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

    boolean existsByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
