package com.servinetcomputers.api.module.platform.persistence;

import com.servinetcomputers.api.module.platform.persistence.entity.PlatformTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
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
    Integer calculateTotalByPlatformIdAndDateBetween(int platformId, LocalDate startDate, LocalDate endDate);

    int countByPlatformIdAndEnabledTrueAndDateBetween(int platformId, LocalDate startDate, LocalDate endDate);

    List<PlatformTransfer> findAllByPlatformIdAndEnabledTrueAndDateBetweenOrderByDateDesc(Integer platformId, LocalDate startDate, LocalDate endDate);

    List<PlatformTransfer> findAllByCreatedByAndEnabledTrueAndDateBetween(String createdBy, LocalDate startDate, LocalDate endDate);
}
