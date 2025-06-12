package com.servinetcomputers.api.module.cashregister.application.port.out;

import com.servinetcomputers.api.module.cashregister.domain.CashRegister;

/**
 * Puerto de escritura y salida para cajas registradoras.
 */
public interface CashRegisterWritePort {
    /**
     * Registra una nueva caja registradora.
     *
     * @param cashRegister {@link CashRegister} con la data.
     * @return {@link CashRegister} guardada.
     */
    CashRegister save(CashRegister cashRegister);
}
