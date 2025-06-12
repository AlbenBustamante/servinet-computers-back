package com.servinetcomputers.api.module.cashregister.exception;

import com.servinetcomputers.api.core.exception.NotFoundException;

public class CashRegisterNotFoundByIdException extends NotFoundException {
    public CashRegisterNotFoundByIdException(Integer id) {
        super("Caja registradora no encontrada según el ID: " + id);
    }
}
