package com.servinetcomputers.api.module.platform.persistence.repository;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.module.platform.persistence.JpaPlatformBalanceRepository;
import com.servinetcomputers.api.module.platform.persistence.mapper.PlatformBalanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The {@link PlatformBalanceRepository} implementation.
 */
@RequiredArgsConstructor
@Repository
public class PlatformBalanceRepositoryImpl implements PlatformBalanceRepository {
    private final JpaPlatformBalanceRepository repository;
    private final PlatformBalanceMapper mapper;

    @Override
    public Optional<PlatformBalanceDto> getByPlatformIdBetween(int platformId, LocalDateTime startDate, LocalDateTime endDate) {
        final var platformBalance = repository.findByPlatformIdAndEnabledTrueAndCreatedDateBetween(platformId, startDate, endDate);
        return platformBalance.map(mapper::toDto);
    }

    @Override
    public Optional<PlatformBalanceDto> get(int balanceId) {
        final var balance = repository.findByIdAndEnabledTrue(balanceId);
        return balance.map(mapper::toDto);
    }

    @Override
    public Optional<PlatformBalanceDto> getLastByPlatformId(int platformId) {
        final var balance = repository.findFirstByPlatformIdAndEnabledTrueOrderByCreatedDateDesc(platformId);
        return balance.map(mapper::toDto);
    }

    @Override
    public List<PlatformBalanceDto> getAllBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var balances = repository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return mapper.toDto(balances);
    }

    /*@Override
    public Integer calculateFinalBalanceBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var total = repository.calculateTotalByFinalBalanceAndCreatedDateBetween(startDate, endDate);
        return total != null ? total : 0;
    }*/

    @Override
    public PlatformBalanceDto save(CreatePlatformBalanceDto request) {
        final var entity = mapper.toEntity(request);
        final var newBalance = repository.save(entity);

        return mapper.toDto(newBalance);
    }

    @Override
    public PlatformBalanceDto save(PlatformBalanceDto response) {
        final var entity = mapper.toEntity(response);
        final var newBalance = repository.save(entity);

        return mapper.toDto(newBalance);
    }
}
