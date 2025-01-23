package com.servinetcomputers.api.domain.platform.persistence.repository;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.persistence.mapper.PlatformBalanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;
import static com.servinetcomputers.api.core.security.util.SecurityConstants.SUPERVISOR_AUTHORITY;

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
    public PlatformBalanceResponse save(PlatformBalanceRequest request) {
        final var entity = mapper.toEntity(request);
        final var newBalance = repository.save(entity);

        return mapper.toResponse(newBalance);
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = {SUPERVISOR_AUTHORITY, ADMIN_AUTHORITY})
    @Override
    public PlatformBalanceResponse update(int balanceId, PlatformBalanceRequest request) {
        final var balance = repository.findById(balanceId)
                .orElseThrow(() -> new NotFoundException("Saldo no encontrado: #" + balanceId));

        if (balance.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Saldo no encontrado: #" + balanceId);
        }

        balance.setInitialBalance(request.initialBalance() != null ? request.initialBalance() : balance.getInitialBalance());
        balance.setFinalBalance(request.finalBalance() != null ? request.finalBalance() : balance.getFinalBalance());

        return mapper.toResponse(repository.save(balance));
    }

    @Override
    public boolean delete(int balanceId) {
        final var balance = repository.findById(balanceId);

        if (balance.isEmpty() || !balance.get().getEnabled()) {
            throw new NotFoundException("Saldo no encontrado: #" + balanceId);
        }

        balance.get().setEnabled(false);

        repository.save(balance.get());

        return true;
    }
}
