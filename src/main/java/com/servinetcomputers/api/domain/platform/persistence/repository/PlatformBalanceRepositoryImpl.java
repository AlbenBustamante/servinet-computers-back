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

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;
import static com.servinetcomputers.api.core.security.util.SecurityConstants.SUPERVISOR_AUTHORITY;

/**
 * The {@link PlatformBalanceRepository} implementation.
 */
@RequiredArgsConstructor
@Repository
public class PlatformBalanceRepositoryImpl implements PlatformBalanceRepository {
    private final JpaPlatformBalanceRepository jpaPlatformBalanceRepository;
    private final PlatformBalanceMapper platformBalanceMapper;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = {SUPERVISOR_AUTHORITY, ADMIN_AUTHORITY})
    @Override
    public PlatformBalanceResponse update(int balanceId, PlatformBalanceRequest request) {
        final var balance = jpaPlatformBalanceRepository.findById(balanceId)
                .orElseThrow(() -> new NotFoundException("Saldo no encontrado: #" + balanceId));

        if (balance.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Saldo no encontrado: #" + balanceId);
        }

        balance.setInitialBalance(request.initialBalance() != null ? request.initialBalance() : balance.getInitialBalance());
        balance.setFinalBalance(request.finalBalance() != null ? request.finalBalance() : balance.getFinalBalance());

        return platformBalanceMapper.toResponse(jpaPlatformBalanceRepository.save(balance));
    }

    @Override
    public boolean delete(int balanceId) {
        final var balance = jpaPlatformBalanceRepository.findById(balanceId);

        if (balance.isEmpty() || !balance.get().getEnabled()) {
            throw new NotFoundException("Saldo no encontrado: #" + balanceId);
        }

        balance.get().setEnabled(false);

        jpaPlatformBalanceRepository.save(balance.get());

        return true;
    }
}
