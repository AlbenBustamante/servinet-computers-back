package com.servinetcomputers.api.domain.platform.persistence.repository;

import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.persistence.mapper.PlatformBalanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
    public Optional<PlatformBalanceResponse> get(int platformId, LocalDateTime startDate, LocalDateTime endDate) {
        final var platformBalance = repository.findByPlatformIdAndEnabledTrueAndCreatedDateBetween(platformId, startDate, endDate);
        return platformBalance.map(mapper::toResponse);
    }

    @Override
    public Optional<PlatformBalanceResponse> get(int balanceId) {
        final var balance = repository.findByIdAndEnabledTrue(balanceId);
        return balance.map(mapper::toResponse);
    }

    @Override
    public PlatformBalanceResponse save(PlatformBalanceRequest request) {
        final var entity = mapper.toEntity(request);
        final var newBalance = repository.save(entity);

        return mapper.toResponse(newBalance);
    }

    @Override
    public PlatformBalanceResponse save(PlatformBalanceResponse response) {
        final var entity = mapper.toEntity(response);
        final var newBalance = repository.save(entity);

        return mapper.toResponse(newBalance);
    }
}
