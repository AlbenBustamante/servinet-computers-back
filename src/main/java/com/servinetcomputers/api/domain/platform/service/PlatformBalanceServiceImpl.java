package com.servinetcomputers.api.domain.platform.service;

import com.servinetcomputers.api.domain.platform.abs.IPlatformBalanceService;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The {@link IPlatformBalanceService} implementation.
 */
@Service
@RequiredArgsConstructor
public class PlatformBalanceServiceImpl implements IPlatformBalanceService {

    private final PlatformBalanceRepository platformBalanceRepository;
    private final PlatformBalanceMapper platformBalanceMapper;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformBalanceResponse update(int balanceId, PlatformBalanceRequest request) {
        final var balance = platformBalanceRepository.findById(balanceId)
                .orElseThrow(() -> new NotFoundException("Saldo no encontrado: #" + balanceId));

        if (balance.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Saldo no encontrado: #" + balanceId);
        }

        balance.setInitialBalance(request.initialBalance() != null ? request.initialBalance() : balance.getInitialBalance());
        balance.setFinalBalance(request.finalBalance() != null ? request.finalBalance() : balance.getFinalBalance());

        return platformBalanceMapper.toResponse(platformBalanceRepository.save(balance));
    }

    @Override
    public boolean delete(int balanceId) {
        final var balance = platformBalanceRepository.findById(balanceId);

        if (balance.isEmpty() || !balance.get().getEnabled()) {
            throw new NotFoundException("Saldo no encontrado: #" + balanceId);
        }

        balance.get().setEnabled(false);

        platformBalanceRepository.save(balance.get());

        return true;
    }

}
