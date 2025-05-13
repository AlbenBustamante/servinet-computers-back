package com.servinetcomputers.api.module.safes.persistence;

import com.servinetcomputers.api.module.safes.persistence.entity.SafeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaSafeDetailRepository extends JpaRepository<SafeDetail, Integer> {
    Optional<SafeDetail> findByIdAndEnabledTrue(int id);

    List<SafeDetail> findAllByEnabledTrueAndSafeId(int safeId);

    List<SafeDetail> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT sd.safe.numeral FROM SafeDetail sd " +
            "WHERE sd.id = :id " +
            "AND sd.enabled = true")
    Optional<Integer> findNumeralById(int id);
}
