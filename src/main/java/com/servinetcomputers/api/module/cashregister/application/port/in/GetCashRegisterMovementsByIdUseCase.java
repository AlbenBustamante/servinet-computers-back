package com.servinetcomputers.api.module.cashregister.application.port.in;

import com.servinetcomputers.api.module.cashregister.domain.CashRegisterDetail;

import java.util.List;

/**
 * Obtiene todos los movimientos de una caja registradora según un ID.
 */
public interface GetCashRegisterMovementsByIdUseCase {
    /**
     * Obtiene todos los movimientos de una caja registradora según un {@code ID}.
     *
     * @param id {@code ID} de la caja registradora.
     * @return listado de {@link CashRegisterDetail}.
     */
    List<CashRegisterDetail> getMovements(Integer id);
}
