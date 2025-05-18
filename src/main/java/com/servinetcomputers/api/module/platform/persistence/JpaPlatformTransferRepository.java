package com.servinetcomputers.api.module.platform.persistence;

import com.servinetcomputers.api.module.platform.persistence.entity.PlatformTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The {@link PlatformTransfer} repository.
 */
public interface JpaPlatformTransferRepository extends JpaRepository<PlatformTransfer, Integer> {
    Optional<PlatformTransfer> findByIdAndEnabledTrue(int id);

    @Query("SELECT SUM(pt.value) FROM PlatformTransfer pt " +
            "WHERE pt.platform.id = :platformId " +
            "AND pt.platform.enabled = true " +
            "AND pt.date BETWEEN :startDate AND :endDate")
    Integer calculateTotalByPlatformIdAndDateBetween(
            @Param("platformId") int platformId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    int countByPlatformIdAndEnabledTrueAndDateBetween(int platformId, LocalDateTime startDate, LocalDateTime endDate);

    List<PlatformTransfer> findAllByPlatformIdAndEnabledTrueAndDateBetweenOrderByDateDesc(Integer platformId, LocalDateTime startDate, LocalDateTime endDate);

    List<PlatformTransfer> findAllByCreatedByAndEnabledTrueAndDateBetween(String createdBy, LocalDateTime startDate, LocalDateTime endDate);
}
