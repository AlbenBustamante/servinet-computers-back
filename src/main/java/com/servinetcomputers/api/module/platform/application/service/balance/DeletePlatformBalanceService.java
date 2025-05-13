package com.servinetcomputers.api.module.platform.application.service.balance;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.platform.application.usecase.balance.DeletePlatformBalanceUseCase;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeletePlatformBalanceService implements DeletePlatformBalanceUseCase {
    private final PlatformBalanceRepository repository;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void call(Integer param) {
        final var balance = repository.get(param)
                .orElseThrow(() -> new NotFoundException("No se encontró el saldo: #" + param));

        balance.setEnabled(false);
        repository.save(balance);
    }
}
