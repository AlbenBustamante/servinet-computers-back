package com.servinetcomputers.api.domain.platform.persistence.repository;

import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferResponse;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformTransferRepository;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformTransferRepository;
import com.servinetcomputers.api.domain.platform.persistence.mapper.PlatformTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The platform transfer's repository implementation.
 */
@RequiredArgsConstructor
@Repository
public class PlatformTransferRepositoryImpl implements PlatformTransferRepository {
    private final JpaPlatformTransferRepository repository;
    private final PlatformTransferMapper mapper;

    @Override
    public PlatformTransferResponse save(PlatformTransferRequest request) {
        final var entity = mapper.toEntity(request);
        final var newTransfer = repository.save(entity);

        return mapper.toResponse(newTransfer);
    }

    @Override
    public PlatformTransferResponse save(PlatformTransferResponse response) {
        final var entity = mapper.toEntity(response);
        final var newTransfer = repository.save(entity);

        return mapper.toResponse(newTransfer);
    }

    @Override
    public Optional<PlatformTransferResponse> get(int transferId) {
        final var transfer = repository.findByIdAndEnabledTrue(transferId);
        return transfer.map(mapper::toResponse);
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
}
