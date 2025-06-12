package com.servinetcomputers.api.module.cashregister.application.port.in;

import com.servinetcomputers.api.module.cashregister.application.port.in.command.CreateCashRegisterCommand;
import com.servinetcomputers.api.module.cashregister.domain.CashRegister;

/**
 * Caso de uso para crear y persistir una nueva caja registradora.
 */
public interface CreateCashRegisterUseCase {
    CashRegister create(CreateCashRegisterCommand command);
}
