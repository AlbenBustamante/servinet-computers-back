package com.servinetcomputers.api.module.cashregister.application.port.in;

import com.servinetcomputers.api.module.base.Base;

/**
 * Obtiene la última base registrada según un ID de caja registradora.
 */
public interface GetLastBaseUseCase {
    /**
     * Obtiene la última base registrada según un {@code ID} de caja registradora.
     *
     * @param id {@code ID} de la caja registradora.
     * @return {@link Base} encontrada.
     */
    Base getLastBase(Integer id);
}
