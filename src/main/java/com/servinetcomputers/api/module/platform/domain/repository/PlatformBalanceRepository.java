package com.servinetcomputers.api.module.platform.domain.repository;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The balance's repository.
 */
public interface PlatformBalanceRepository {
    PlatformBalanceDto save(CreatePlatformBalanceDto request);

    PlatformBalanceDto save(PlatformBalanceDto response);

    List<PlatformBalanceDto> getAllByPlatformIdBetween(int platformId, LocalDateTime startDate, LocalDateTime endDate);

    Optional<PlatformBalanceDto> get(int balanceId);

    Optional<PlatformBalanceDto> getLastByPlatformId(int platformId);

    List<PlatformBalanceDto> getAllBetween(LocalDateTime startDate, LocalDateTime endDate);

    //Integer calculateFinalBalanceBetween(LocalDateTime startDate, LocalDateTime endDate);
}
