package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.dto.PlatformTransferRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferResponse;

import java.util.Optional;

/**
 * The transfer's uses case.
 */
public interface IPlatformTransferService {

    /**
     * Create and persist a new transfer.
     *
     * @param request the data to be saved.
     * @return the transfer saved.
     */
    PlatformTransferResponse create(PlatformTransferRequest request);

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
