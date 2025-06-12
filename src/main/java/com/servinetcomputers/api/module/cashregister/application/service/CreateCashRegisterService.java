package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.CreateCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.command.CreateCashRegisterCommand;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterReadPort;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterWritePort;
import com.servinetcomputers.api.module.cashregister.domain.CashRegister;
import com.servinetcomputers.api.module.cashregister.exception.CashRegisterAlreadyExistsByNumeralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CreateCashRegisterService implements CreateCashRegisterUseCase {
    private final CashRegisterReadPort readPort;
    private final CashRegisterWritePort writePort;

    @Override
    @Secured(value = ADMIN_AUTHORITY)
    public CashRegister create(CreateCashRegisterCommand command) {
        if (readPort.existsByNumeral(command.numeral())) {
            throw new CashRegisterAlreadyExistsByNumeralException(command.numeral());
        }

        final var cashRegister = CashRegister.create(command.numeral(), command.description(), command.status());

        return writePort.save(cashRegister);
    }
}
