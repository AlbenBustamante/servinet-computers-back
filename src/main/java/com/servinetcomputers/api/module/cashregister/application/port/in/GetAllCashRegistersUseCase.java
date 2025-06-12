package com.servinetcomputers.api.module.cashregister.application.port.in;

import com.servinetcomputers.api.module.cashregister.domain.CashRegister;

import java.util.List;

/**
 * Obtiene todas las cajas registradoras.
 */
public interface GetAllCashRegistersUseCase {
    /**
     * Obtiene todas las cajas registradoras.
     *
     * @return listado de cajas registradoras.
     */
    List<CashRegister> getAll();
}
