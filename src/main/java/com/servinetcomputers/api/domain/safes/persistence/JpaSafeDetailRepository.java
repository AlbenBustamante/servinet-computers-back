package com.servinetcomputers.api.domain.safes.persistence;

import com.servinetcomputers.api.domain.safes.persistence.entity.SafeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaSafeDetailRepository extends JpaRepository<SafeDetail, Integer> {
    Optional<SafeDetail> findByIdAndEnabledTrue(int id);

    List<SafeDetail> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
