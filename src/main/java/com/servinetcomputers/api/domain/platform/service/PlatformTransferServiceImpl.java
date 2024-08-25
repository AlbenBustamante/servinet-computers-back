package com.servinetcomputers.api.domain.platform.service;

import com.servinetcomputers.api.domain.platform.abs.IPlatformTransferService;
import com.servinetcomputers.api.domain.platform.abs.PlatformRepository;
import com.servinetcomputers.api.domain.platform.abs.PlatformTransferMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformTransferRepository;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The platform transfer's service implementation.
 */
@RequiredArgsConstructor
@Service
public class PlatformTransferServiceImpl implements IPlatformTransferService {

    private final PlatformTransferRepository repository;
    private final PlatformTransferMapper mapper;
    private final PlatformRepository platformRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformTransferResponse create(PlatformTransferRequest request) {
        final var platform = platformRepository.findById(request.platformId())
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: #" + request.platformId()));

        if (!platform.getEnabled()) {
            throw new NotFoundException("Plataforma no encontrada: #" + request.platformId());
        }

        final var transfer = mapper.toEntity(request);
        transfer.setPlatform(platform);

        return mapper.toResponse(repository.save(transfer));
    }

    @Transactional(readOnly = true)
    @Override
    public PlatformTransferResponse get(int transferId) {
        final var transfer = repository.findById(transferId)
                .orElseThrow(() -> new NotFoundException("Transferencia no encontrada: " + transferId));

        if (transfer.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Transferencia no encontrada: " + transferId);
        }

        return mapper.toResponse(transfer);
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformTransferResponse update(int transferId, PlatformTransferRequest request) {
        final var transfer = repository.findById(transferId)
                .orElseThrow(() -> new NotFoundException("Transferencia no encontrada: " + transferId));

        if (transfer.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Transferencia no encontrada: " + transferId);
        }

        if (request.platformId() != null) {
            final var platform = platformRepository.findById(request.platformId())
                    .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: #" + request.platformId()));

            if (!platform.getEnabled()) {
                throw new NotFoundException("Plataforma no encontrada: #" + request.platformId());
            }

            transfer.setPlatform(platform);
        }

        transfer.setValue(request.value() != null ? request.value() : transfer.getValue());

        return mapper.toResponse(repository.save(transfer));
    }

    @Override
    public boolean delete(int transferId) {
        final var transferFound = repository.findById(transferId);

        if (transferFound.isEmpty()) {
            return false;
        }

        transferFound.get().setEnabled(false);

        repository.save(transferFound.get());

        return true;
    }

}
