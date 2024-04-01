package com.servinetcomputers.api.domain.transfer.abs;

import com.servinetcomputers.api.domain.PageResponse;
import com.servinetcomputers.api.domain.transfer.model.dto.TransferRequest;
import com.servinetcomputers.api.domain.transfer.model.dto.TransferResponse;

import java.util.Optional;

/**
 * The transfer's uses case.
 */
public interface ITransferService {

    /**
     * Create and persist a new transfer.
     *
     * @param request the data to be saved.
     * @return the transfer saved.
     */
    PageResponse<TransferResponse> create(TransferRequest request);

    /**
     * Search an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @return an {@link Optional} of the transfer found.
     */
    PageResponse<TransferResponse> get(int transferId);

    /**
     * Update an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @param request    the data to be updated.
     * @return an {@link Optional} of the transfer updated.
     */
    PageResponse<TransferResponse> update(int transferId, TransferRequest request);

    /**
     * Disable an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @return {@code true} if the transfer was disabled.
     */
    boolean delete(int transferId);

}
