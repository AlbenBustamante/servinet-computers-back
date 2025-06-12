package com.servinetcomputers.api.module.cashregister.application.port.out;

import com.servinetcomputers.api.module.cashregister.domain.CashRegister;

import java.util.List;

/**
 * Puerto de lectura y salida para las cajas registradoras.
 */
public interface CashRegisterReadPort {
    /**
     * Obtiene una caja registradora según un ID.
     *
     * @param id {@code ID} de la caja registradora.
     * @return {@link CashRegister} encontrada.
     */
    CashRegister getById(int id);

    /**
     * Obtiene todas las cajas registradoras.
     *
     * @return listado de cajas registradoras.
     */
    List<CashRegister> getAll();

    /**
     * Obtiene sólo los {@code IDs} de todas las cajas registradoras.
     *
     * @return listado de {@code IDs}.
     */
    List<Integer> getAllIds();

    /**
     * Chequea si ya existe una caja registradora según un {@code ID}.
     *
     * @param id {@code ID} a chequear.
     * @return {@code true} si se encuentra un registro.
     */
    boolean existsById(int id);

    /**
     * Chequea si ya existe una caja registradora según un numeral.
     *
     * @param numeral numeral a chequear.
     * @return {@code true} si se encuentra un registro.
     */
    boolean existsByNumeral(int numeral);
}
