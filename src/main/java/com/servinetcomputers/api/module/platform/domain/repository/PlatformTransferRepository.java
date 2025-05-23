package com.servinetcomputers.api.module.platform.domain.repository;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformTransferWithVouchersDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The transfer's repository.
 */
public interface PlatformTransferRepository {
    PlatformTransferDto save(CreatePlatformTransferWithVouchersDto request);

    PlatformTransferDto save(PlatformTransferDto response);

    Optional<PlatformTransferDto> get(int id);

    List<PlatformTransferDto> getAllByPlatformIdBetweenOrderByDateDesc(Integer platformId, LocalDate startDate, LocalDate endDate);

    List<PlatformTransferDto> getAllByCodeBetween(String code, LocalDate startDate, LocalDate endDate);

    int getPlatformTransfersAmountBetween(int platformId, LocalDate startDate, LocalDate endDate);

    int getPlatformTransfersTotalBetween(int platformId, LocalDate startDate, LocalDate endDate);
}
