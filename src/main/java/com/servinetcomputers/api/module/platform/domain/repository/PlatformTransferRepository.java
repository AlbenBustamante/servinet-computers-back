package com.servinetcomputers.api.module.platform.domain.repository;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformTransferWithVouchersDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The transfer's repository.
 */
public interface PlatformTransferRepository {
    PlatformTransferDto save(CreatePlatformTransferWithVouchersDto request);

    PlatformTransferDto save(PlatformTransferDto response);

    Optional<PlatformTransferDto> get(int id);

    List<PlatformTransferDto> getAllByPlatformIdBetween(Integer platformId, LocalDateTime startDate, LocalDateTime endDate);

    List<PlatformTransferDto> getAllByCodeBetween(String code, LocalDateTime startDate, LocalDateTime endDate);

    int getPlatformTransfersAmount(int platformId, LocalDateTime startDate, LocalDateTime endDate);

    int getPlatformTransfersTotal(int platformId, LocalDateTime startDate, LocalDateTime endDate);
}
