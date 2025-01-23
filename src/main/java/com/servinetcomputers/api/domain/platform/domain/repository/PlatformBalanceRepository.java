package com.servinetcomputers.api.domain.platform.domain.repository;

import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceResponse;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The balance's repository.
 */
public interface PlatformBalanceRepository {
    PlatformBalanceResponse save(PlatformBalanceRequest request);

    Optional<PlatformBalanceResponse> get(int platformId, LocalDateTime startDate, LocalDateTime endDate);

    PlatformBalanceResponse update(int balanceId, PlatformBalanceRequest request);

    boolean delete(int balanceId);
}
