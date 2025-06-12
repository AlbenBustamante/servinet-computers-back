package com.servinetcomputers.api.module.cashregister.application.port.in;

import com.servinetcomputers.api.module.cashregister.application.port.in.command.UpdateCashRegisterCommand;
import com.servinetcomputers.api.module.cashregister.domain.CashRegister;

/**
 * Caso de uso para actualizar una caja registradora ya existente.
 */
public interface UpdateCashRegisterUseCase {
    /**
     * Actualiza una caja registradora existente según un {@code ID}.
     *
     * @param id      {@code ID} de la caja registradora.
     * @param command Comando para actualizar la caja registradora.
     * @return {@link CashRegister} actualizada.
     */
    CashRegister update(Integer id, UpdateCashRegisterCommand command);
}
