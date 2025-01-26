package com.servinetcomputers.api.domain.platform.domain.repository;

import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferResponse;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The transfer's repository.
 */
public interface PlatformTransferRepository {
    PlatformTransferResponse save(PlatformTransferRequest request);

    PlatformTransferResponse save(PlatformTransferResponse response);

    Optional<PlatformTransferResponse> get(int id);

    int getPlatformTransfersAmount(int platformId, LocalDateTime initialDate, LocalDateTime finalDate);

    int getPlatformTransfersTotal(int platformId, LocalDateTime startDate, LocalDateTime endDate);
}
