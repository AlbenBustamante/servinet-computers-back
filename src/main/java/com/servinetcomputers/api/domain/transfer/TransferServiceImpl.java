package com.servinetcomputers.api.domain.transfer;

import com.servinetcomputers.api.domain.DataResponse;
import com.servinetcomputers.api.domain.PageResponse;
import com.servinetcomputers.api.domain.platform.abs.PlatformRepository;
import com.servinetcomputers.api.domain.platform.model.Platform;
import com.servinetcomputers.api.domain.transfer.abs.ITransferService;
import com.servinetcomputers.api.domain.transfer.abs.TransferMapper;
import com.servinetcomputers.api.domain.transfer.abs.TransferRepository;
import com.servinetcomputers.api.domain.transfer.model.Transfer;
import com.servinetcomputers.api.domain.transfer.model.dto.TransferRequest;
import com.servinetcomputers.api.domain.transfer.model.dto.TransferResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = AppException.class)
    // @Secured(value = CAMPUS_AUTHORITY)
    @Override
    public PageResponse<TransferResponse> create(TransferRequest request) {
        final var platform = platformRepository.findByName(request.platformName())
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: " + request.platformName()));

        final List<Transfer> transfers = new ArrayList<>(request.amount());

        for (int i = 0; i < request.amount(); i++) {
            final var transfer = mapper.toEntity(request);
            transfer.setPlatform(platform);
            transfer.setPlatformId(platform.getId());

            transfers.add(transfer);
        }

        final var response = mapper.toResponses(repository.saveAll(transfers));

        return new PageResponse<>(201, true, new DataResponse<>(response.size(), 1, 1, response));
    }

    @Transactional(readOnly = true)
    // @Secured(value = CAMPUS_AUTHORITY)
    @Override
    public PageResponse<TransferResponse> get(int transferId) {
        final var transfer = repository.findById(transferId)
                .orElseThrow(() -> new NotFoundException("Transferencia no encontrada: " + transferId));

        if (transfer.getIsAvailable().equals(Boolean.FALSE)) {
            throw new NotFoundException("Transferencia no encontrada: " + transferId);
        }

        final var response = mapper.toResponse(transfer);

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Transactional(rollbackFor = AppException.class)
    // @Secured(value = CAMPUS_AUTHORITY)
    @Override
    public PageResponse<TransferResponse> update(int transferId, TransferRequest request) {
        final var transfer = repository.findById(transferId)
                .orElseThrow(() -> new NotFoundException("Transferencia no encontrada: " + transferId));

        if (transfer.getIsAvailable().equals(Boolean.FALSE)) {
            throw new NotFoundException("Transferencia no encontrada: " + transferId);
        }

        final Platform[] platform = {transfer.getPlatform()};

        if (request.platformName() != null) {
            platformRepository.findByName(request.platformName())
                    .ifPresentOrElse(
                            (res -> platform[0] = res),
                            () -> {
                                throw new NotFoundException("Plataforma no encontrada: " + request.platformName());
                            });
        }

        transfer.setPlatform(platform[0]);
        transfer.setValue(request.value());

        final var response = mapper.toResponse(repository.save(transfer));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    // @Secured(value = CAMPUS_AUTHORITY)
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
