package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.entity.PlatformTransfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The {@link PlatformTransfer} repository.
 */
public interface PlatformTransferRepository extends JpaRepository<PlatformTransfer, Integer> {

    @Query("SELECT SUM(pt.value) FROM PlatformTransfer pt " +
            "WHERE pt.platform.id = :platformId " +
            "AND pt.platform.enabled = true " +
            "AND pt.createdDate BETWEEN :startDate AND :endDate")
    Integer calculateTotalByPlatformIdAndCreatedDateBetween(
            @Param("platformId") int platformId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    int countByPlatformIdAndEnabledTrueAndCreatedDateBetween(int platformId, LocalDateTime startDate, LocalDateTime endDate);

    List<PlatformTransfer> findAllByCreatedByAndEnabledTrueAndCreatedDateBetween(String createdBy, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Search all the transfers created between two dates.
     *
     * @param firstDate the first date.
     * @param lastDate  the second and last date.
     * @param pageable  the pageable.
     * @return a {@link Page} of all the transfers found.
     */
    Page<PlatformTransfer> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime firstDate, LocalDateTime lastDate, Pageable pageable);

    /**
     * Search all the transfers created between two dates without pageable.
     *
     * @param firstDate the first date.
     * @param lastDate  the last date.
     * @return all the transfers found.
     */
    List<PlatformTransfer> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime firstDate, LocalDateTime lastDate);

}
