package com.servinetcomputers.api.domain.platform.persistence.repository;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferResponse;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformTransferRepository;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformRepository;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformTransferRepository;
import com.servinetcomputers.api.domain.platform.persistence.mapper.PlatformTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * The platform transfer's repository implementation.
 */
@RequiredArgsConstructor
@Repository
public class PlatformTransferRepositoryImpl implements PlatformTransferRepository {
    private final JpaPlatformTransferRepository repository;
    private final PlatformTransferMapper mapper;
    private final JpaPlatformRepository jpaPlatformRepository;

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

    @Override
    public PlatformTransferResponse save(PlatformTransferRequest request) {
        final var entity = mapper.toEntity(request);
        final var newTransfer = repository.save(entity);

        return mapper.toResponse(newTransfer);
    }

    @Override
    public int getPlatformTransfersAmount(int platformId, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.countByPlatformIdAndEnabledTrueAndCreatedDateBetween(platformId, startDate, endDate);
    }

    @Override
    public int getPlatformTransfersTotal(int platformId, LocalDateTime startDate, LocalDateTime endDate) {
        final var total = repository.calculateTotalByPlatformIdAndCreatedDateBetween(platformId, startDate, endDate);
        return total != null ? total : 0;
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformTransferResponse update(int transferId, PlatformTransferRequest request) {
        final var transfer = repository.findById(transferId)
                .orElseThrow(() -> new NotFoundException("Transferencia no encontrada: " + transferId));

        if (transfer.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Transferencia no encontrada: " + transferId);
        }

        if (request.getPlatformId() != null) {
            final var platform = jpaPlatformRepository.findById(request.getPlatformId())
                    .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: #" + request.getPlatformId()));

            if (!platform.getEnabled()) {
                throw new NotFoundException("Plataforma no encontrada: #" + request.getPlatformId());
            }

            transfer.setPlatform(platform);
        }

        transfer.setValue(request.getValue() != null ? request.getValue() : transfer.getValue());

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
