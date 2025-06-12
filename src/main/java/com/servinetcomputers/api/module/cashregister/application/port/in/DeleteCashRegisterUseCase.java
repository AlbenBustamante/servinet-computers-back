package com.servinetcomputers.api.module.cashregister.application.port.in;

/**
 * Elimina de forma segura una caja registradora.
 */
public interface DeleteCashRegisterUseCase {
    void delete(Integer id);
}
