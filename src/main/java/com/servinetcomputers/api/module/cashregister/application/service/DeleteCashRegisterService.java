package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.module.cashregister.application.port.in.DeleteCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterReadPort;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterWritePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = AppException.class)
public class DeleteCashRegisterService implements DeleteCashRegisterUseCase {
    private final CashRegisterReadPort readPort;
    private final CashRegisterWritePort writePort;

    @Override
    @Secured(value = ADMIN_AUTHORITY)
    public void delete(Integer id) {
        final var cashRegister = readPort.getById(id);
        writePort.save(cashRegister.delete());
    }
}
