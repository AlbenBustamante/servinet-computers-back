package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.PageRequest;
import com.servinetcomputers.api.dto.request.TransferRequest;
import com.servinetcomputers.api.dto.response.DataResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.TransferResponse;
import com.servinetcomputers.api.exception.CampusNotFoundException;
import com.servinetcomputers.api.exception.PlatformNameNotFoundException;
import com.servinetcomputers.api.exception.TransferNotFoundException;
import com.servinetcomputers.api.exception.TransferUnavailableException;
import com.servinetcomputers.api.mapper.TransferMapper;
import com.servinetcomputers.api.model.Platform;
import com.servinetcomputers.api.model.Transfer;
import com.servinetcomputers.api.repository.CampusRepository;
import com.servinetcomputers.api.repository.PlatformRepository;
import com.servinetcomputers.api.repository.TransferRepository;
import com.servinetcomputers.api.service.ITransferService;
import com.servinetcomputers.api.util.ICurrencyFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The transfer's service implementation.
 */
@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements ITransferService {

    private final TransferRepository repository;
    private final TransferMapper mapper;
    private final PlatformRepository platformRepository;
    private final CampusRepository campusRepository;
    private final ICurrencyFormatter currencyFormatter;

    @Override
    public PageResponse<TransferResponse> create(TransferRequest request) {
        final var platformFound = platformRepository.findByName(request.platformName());

        if (platformFound.isEmpty()) {
            throw new PlatformNameNotFoundException(request.platformName());
        }

        final List<Transfer> transfers = new ArrayList<>(request.amount());

        for (int i = 0; i < request.amount(); i++) {
            final var transfer = mapper.toEntity(request);
            transfer.setPlatform(platformFound.get());
            transfer.setPlatformId(platformFound.get().getId());

            transfers.add(transfer);
        }

        final var response = mapper.toResponses(repository.saveAll(transfers), currencyFormatter);

        return new PageResponse<>(201, true, new DataResponse<>(response.size(), 1, 1, response));
    }

    @Override
    public PageResponse<TransferResponse> get(int transferId) {
        final var transferFound = repository.findById(transferId);

        if (transferFound.isEmpty()) {
            throw new TransferNotFoundException(transferId);
        }

        if (Boolean.FALSE.equals(transferFound.get().getIsAvailable())) {
            throw new TransferUnavailableException(transferId);
        }

        final var response = mapper.toResponse(transferFound.get(), currencyFormatter);

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Override
    public PageResponse<TransferResponse> getAllByCampusIdCreationDateBetween(int campusId, LocalDateTime startDate, LocalDateTime endDate, PageRequest pageRequest) {
        if (!campusRepository.existsById(campusId)) {
            throw new CampusNotFoundException(campusId);
        }

        final var page = repository.findAllByCampusIdAndIsAvailableAndCreatedAtBetween(campusId, true, startDate, endDate, pageRequest.toPageable());
        final var response = mapper.toResponses(page.getContent(), currencyFormatter);

        return new PageResponse<>(200, true, new DataResponse<>(page.getTotalElements(), page.getNumber(), page.getTotalPages(), response));
    }

    @Override
    public PageResponse<TransferResponse> update(int transferId, TransferRequest request) {
        final var transferFound = repository.findById(transferId);

        if (transferFound.isEmpty()) {
            throw new TransferNotFoundException(transferId);
        }

        final var transfer = transferFound.get();

        if (Boolean.FALSE.equals(transfer.getIsAvailable())) {
            throw new TransferUnavailableException(transferId);
        }

        final Platform[] platform = {transfer.getPlatform()};

        if (request.platformName() != null) {
            platformRepository.findByName(request.platformName())
                    .ifPresentOrElse(
                            (res -> platform[0] = res),
                            () -> {
                                throw new PlatformNameNotFoundException(request.platformName());
                            });
        }

        transfer.setPlatform(platform[0]);
        transfer.setValue(request.value());

        final var response = mapper.toResponse(repository.save(transfer), currencyFormatter);

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Override
    public boolean delete(int transferId) {
        final var transferFound = repository.findById(transferId);

        if (transferFound.isEmpty()) {
            return false;
        }

        transferFound.get().setIsAvailable(false);

        repository.save(transferFound.get());

        return true;
    }

}
