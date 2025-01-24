package com.servinetcomputers.api.domain.platform.persistence;

import com.servinetcomputers.api.domain.platform.persistence.entity.PlatformBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The {@link PlatformBalance} repository.
 */
public interface JpaPlatformBalanceRepository extends JpaRepository<PlatformBalance, Integer> {
    Optional<PlatformBalance> findByIdAndEnabledTrue(int id);

    Optional<PlatformBalance> findByPlatformIdAndEnabledTrueAndCreatedDateBetween(int platformId, LocalDateTime startDate, LocalDateTime endDate);

    List<PlatformBalance> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(pb.finalBalance) FROM PlatformBalance pb " +
            "WHERE pb.enabled = true " +
            "AND pb.createdDate BETWEEN :startDate AND :endDate")
    Integer calculateTotalByFinalBalanceAndCreatedDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
