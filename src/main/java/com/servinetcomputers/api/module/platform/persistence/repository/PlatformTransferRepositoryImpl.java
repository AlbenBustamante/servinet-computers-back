package com.servinetcomputers.api.module.platform.persistence.repository;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformTransferWithVouchersDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import com.servinetcomputers.api.module.platform.persistence.JpaPlatformTransferRepository;
import com.servinetcomputers.api.module.platform.persistence.mapper.PlatformTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
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
    public PlatformTransferDto save(CreatePlatformTransferWithVouchersDto request) {
        final var entity = mapper.toEntity(request);
        final var newTransfer = repository.save(entity);

        return mapper.toDto(newTransfer);
    }

    @Override
    public PlatformTransferDto save(PlatformTransferDto response) {
        final var entity = mapper.toEntity(response);
        final var newTransfer = repository.save(entity);

        return mapper.toDto(newTransfer);
    }

    @Override
    public Optional<PlatformTransferDto> get(int transferId) {
        final var transfer = repository.findByIdAndEnabledTrue(transferId);
        return transfer.map(mapper::toDto);
    }

    @Override
    public List<PlatformTransferDto> getAllByPlatformIdBetweenOrderByDateDesc(Integer platformId, LocalDate startDate, LocalDate endDate) {
        final var transfers = repository.findAllByPlatformIdAndEnabledTrueAndDateBetweenOrderByDateDesc(platformId, startDate, endDate);
        return mapper.toDto(transfers);
    }

    @Override
    public List<PlatformTransferDto> getAllByCodeBetween(String code, LocalDate startDate, LocalDate endDate) {
        final var transfers = repository.findAllByCreatedByAndEnabledTrueAndDateBetween(code, startDate, endDate);
        return mapper.toDto(transfers);
    }

    @Override
    public int getPlatformTransfersAmountBetween(int platformId, LocalDate startDate, LocalDate endDate) {
        return repository.countByPlatformIdAndEnabledTrueAndDateBetween(platformId, startDate, endDate);
    }

    @Override
    public int getPlatformTransfersTotalBetween(int platformId, LocalDate startDate, LocalDate endDate) {
        final var total = repository.calculateTotalByPlatformIdAndDateBetween(platformId, startDate, endDate);
        return total != null ? total : 0;
    }
}
