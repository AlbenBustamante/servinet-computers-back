package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.entity.SafeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SafeDetailRepository extends JpaRepository<SafeDetail, Integer> {

    List<SafeDetail> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
