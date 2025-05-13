package com.servinetcomputers.api.module.platform.application.service.balance;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.platform.application.usecase.balance.UpdatePlatformBalanceUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.UpdatePlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;
import static com.servinetcomputers.api.core.util.constants.SecurityConstants.SUPERVISOR_AUTHORITY;

@RequiredArgsConstructor
@Service
public class UpdatePlatformBalanceService implements UpdatePlatformBalanceUseCase {
    private final PlatformBalanceRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = {SUPERVISOR_AUTHORITY, ADMIN_AUTHORITY})
    @Override
    public PlatformBalanceDto call(Integer balanceId, UpdatePlatformBalanceDto dto) {
        final var balance = repository.get(balanceId)
                .orElseThrow(() -> new NotFoundException("Saldo no encontrado: #" + balanceId));

        balance.setInitialBalance(dto.initialBalance() != null ? dto.initialBalance() : balance.getInitialBalance());
        balance.setFinalBalance(dto.finalBalance() != null ? dto.finalBalance() : balance.getFinalBalance());

        return repository.save(balance);
    }
}
