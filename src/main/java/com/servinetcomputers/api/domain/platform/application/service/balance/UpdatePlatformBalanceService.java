package com.servinetcomputers.api.domain.platform.application.service.balance;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.platform.application.usecase.balance.UpdatePlatformBalanceUseCase;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.UpdatePlatformBalanceDto;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;
import static com.servinetcomputers.api.core.security.util.SecurityConstants.SUPERVISOR_AUTHORITY;

@RequiredArgsConstructor
@Service
public class UpdatePlatformBalanceService implements UpdatePlatformBalanceUseCase {
    private final PlatformBalanceRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = {SUPERVISOR_AUTHORITY, ADMIN_AUTHORITY})
    @Override
    public PlatformBalanceResponse call(UpdatePlatformBalanceDto param) {
        final var balance = repository.get(param.balanceId())
                .orElseThrow(() -> new NotFoundException("Saldo no encontrado: #" + param.balanceId()));

        balance.setInitialBalance(param.initialBalance() != null ? param.initialBalance() : balance.getInitialBalance());
        balance.setFinalBalance(param.finalBalance() != null ? param.finalBalance() : balance.getFinalBalance());

        return repository.save(balance);
    }
}
