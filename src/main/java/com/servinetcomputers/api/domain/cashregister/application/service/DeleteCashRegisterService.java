package com.servinetcomputers.api.domain.cashregister.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import com.servinetcomputers.api.domain.cashregister.application.usecase.DeleteCashRegisterUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class DeleteCashRegisterService implements DeleteCashRegisterUseCase {
    private final CashRegisterRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public void call(Integer param) {
        final var cashRegister = repository.get(param)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja: #" + param));

        cashRegister.setStatus(CashRegisterStatus.DISABLED);
        cashRegister.setEnabled(false);

        repository.save(cashRegister);
    }
}
