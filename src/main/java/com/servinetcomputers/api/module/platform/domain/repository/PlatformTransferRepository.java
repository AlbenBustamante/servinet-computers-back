package com.servinetcomputers.api.module.platform.domain.repository;

import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferRequest;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The transfer's repository.
 */
public interface PlatformTransferRepository {
    PlatformTransferResponse save(PlatformTransferRequest request);

    PlatformTransferResponse save(PlatformTransferResponse response);

    Optional<PlatformTransferResponse> get(int id);

    List<PlatformTransferResponse> getAllByCodeBetween(String code, LocalDateTime startDate, LocalDateTime endDate);

    int getPlatformTransfersAmount(int platformId, LocalDateTime startDate, LocalDateTime endDate);

    int getPlatformTransfersTotal(int platformId, LocalDateTime startDate, LocalDateTime endDate);
}
