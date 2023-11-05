package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.TransferRequest;
import com.servinetcomputers.api.dto.response.TransferResponse;
import com.servinetcomputers.api.mapper.TransferMapper;
import com.servinetcomputers.api.model.Platform;
import com.servinetcomputers.api.model.Transfer;
import com.servinetcomputers.api.repository.PlatformRepository;
import com.servinetcomputers.api.repository.TransferRepository;
import com.servinetcomputers.api.service.ITransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The transfer's service implementation.
 */
@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements ITransferService {

    private final TransferRepository repository;
    private final TransferMapper mapper;
    private final PlatformRepository platformRepository;

    @Override
    public TransferResponse create(TransferRequest request) {
        final var platformFound = platformRepository.findByName(request.platformName());

        if (platformFound.isEmpty()) {
            throw new RuntimeException("The platform doesn't exists");
        }

        final List<Transfer> transfers = new ArrayList<>();

        for (int i = 0; i < request.amount(); i++) {
            final var transfer = mapper.toEntity(request);
            transfer.setPlatform(platformFound.get());

            transfers.add(transfer);
        }

        return mapper.toResponse(repository.saveAll(transfers).get(0));
    }

    @Override
    public Optional<TransferResponse> get(int transferId) {
        final var transferFound = repository.findById(transferId);

        if (transferFound.isEmpty()) {
            return Optional.empty();
        }

        if (!transferFound.get().getIsAvailable()) {
            throw new RuntimeException("The transfer is disabled");
        }

        return transferFound.map(mapper::toResponse);
    }

    @Override
    public List<TransferResponse> getAllByCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return mapper.toResponses(repository.findAllByCreatedAtBetweenAndIsAvailable(startDate, endDate, true));
    }

    @Override
    public Optional<TransferResponse> update(int transferId, TransferRequest request) {
        final var transferFound = repository.findById(transferId);

        if (transferFound.isEmpty()) {
            return Optional.empty();
        }

        final var transfer = transferFound.get();

        if (!transfer.getIsAvailable()) {
            throw new RuntimeException("The transfer is disabled");
        }

        final Platform[] platform = {transfer.getPlatform()};

        if (request.platformName() != null) {
            platformRepository.findByName(request.platformName())
                    .ifPresent(res -> platform[0] = res);
        }

        transfer.setPlatform(platform[0]);
        transfer.setValue(request.value());

        return Optional.of(mapper.toResponse(repository.save(transfer)));
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
