package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import com.servinetcomputers.api.module.cashregister.application.usecase.DeleteCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterRepository;
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
    public void call(Integer cashRegisterId) {
        final var cashRegister = repository.get(cashRegisterId)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja: #" + cashRegisterId));

        cashRegister.setDescription("ELIMINADO: " + cashRegister.getDescription() + " #" + cashRegisterId);
        cashRegister.setStatus(CashRegisterStatus.DISABLED);
        cashRegister.setEnabled(false);

        repository.save(cashRegister);
    }
}
