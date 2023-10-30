package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.request.TransferRequest;
import com.servinetcomputers.api.dto.response.TransferResponse;

import java.time.LocalDateTime;
import java.util.List;
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
    TransferResponse create(TransferRequest request);

    /**
     * Search an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @return an {@link Optional} of the transfer found.
     */
    Optional<TransferResponse> get(int transferId);

    /**
     * Search all the existing and available transfers between two dates.
     *
     * <p>If {@code startDate} or {@code endDate} or both are null, they'll be the same day which is consulted by default.</p>
     *
     * @param startDate the first date.
     * @param endDate   the last date.
     * @return a list of the all transfers found.
     */
    List<TransferResponse> getAllByCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Update an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @param request    the data to be updated.
     * @return an {@link Optional} of the transfer updated.
     */
    Optional<TransferResponse> update(int transferId, TransferRequest request);

    /**
     * Disable an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @return {@code true} if the transfer was disabled.
     */
    boolean delete(int transferId);

}
