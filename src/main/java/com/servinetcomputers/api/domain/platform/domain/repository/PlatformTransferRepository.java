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

    int getPlatformTransfersAmount(int platformId, LocalDateTime initialDate, LocalDateTime finalDate);

    int getPlatformTransfersTotal(int platformId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Search an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @return an {@link Optional} of the transfer found.
     */
    PlatformTransferResponse get(int transferId);

    /**
     * Update an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @param request    the data to be updated.
     * @return an {@link Optional} of the transfer updated.
     */
    PlatformTransferResponse update(int transferId, PlatformTransferRequest request);

    /**
     * Disable an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @return {@code true} if the transfer was disabled.
     */
    boolean delete(int transferId);
}
