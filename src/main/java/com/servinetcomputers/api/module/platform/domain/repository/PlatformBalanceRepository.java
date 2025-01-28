package com.servinetcomputers.api.module.platform.domain.repository;

import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The balance's repository.
 */
public interface PlatformBalanceRepository {
    PlatformBalanceResponse save(PlatformBalanceRequest request);

    PlatformBalanceResponse save(PlatformBalanceResponse response);

    Optional<PlatformBalanceResponse> get(int platformId, LocalDateTime startDate, LocalDateTime endDate);

    Optional<PlatformBalanceResponse> get(int balanceId);

    List<PlatformBalanceResponse> getAllBetween(LocalDateTime startDate, LocalDateTime endDate);

    Integer calculateFinalBalanceBetween(LocalDateTime startDate, LocalDateTime endDate);
}
