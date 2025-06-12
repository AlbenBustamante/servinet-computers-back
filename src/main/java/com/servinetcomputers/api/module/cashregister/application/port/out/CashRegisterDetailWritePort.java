package com.servinetcomputers.api.module.cashregister.application.port.out;

import com.servinetcomputers.api.module.cashregister.domain.CashRegisterDetail;

/**
 * Puerto de escritura y salida para movimientos de caja registradora.
 */
public interface CashRegisterDetailWritePort {
    /**
     * Registra un nuevo movimiento de caja registradora.
     *
     * @param cashRegisterDetail modelo a registrar.
     * @return {@link CashRegisterDetail} registrado.
     */
    CashRegisterDetail save(CashRegisterDetail cashRegisterDetail);
}
