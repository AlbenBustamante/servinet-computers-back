package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import com.servinetcomputers.api.module.cashregister.application.usecase.UpdateCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.UpdateCashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class UpdateCashRegisterService implements UpdateCashRegisterUseCase {
    private final CashRegisterRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public CashRegisterDto call(Integer id, UpdateCashRegisterDto updateCashRegisterDto) {
        final var cashRegister = repository.get(id)
                .orElseThrow(() -> new NotFoundException("Caja registradora no encontrada: #" + id));

        cashRegister.setDescription(updateCashRegisterDto.description() != null ? updateCashRegisterDto.description() : cashRegister.getDescription());

        if (cashRegister.getStatus() != CashRegisterStatus.OCCUPIED) {
            cashRegister.setStatus(updateCashRegisterDto.disabled() ? CashRegisterStatus.DISABLED : CashRegisterStatus.AVAILABLE);
        }

        return repository.save(cashRegister);
    }
}
