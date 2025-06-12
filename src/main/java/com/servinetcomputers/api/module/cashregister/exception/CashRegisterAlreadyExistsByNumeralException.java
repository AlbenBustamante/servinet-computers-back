package com.servinetcomputers.api.module.cashregister.exception;

import com.servinetcomputers.api.core.exception.AlreadyExistsException;

public class CashRegisterAlreadyExistsByNumeralException extends AlreadyExistsException {
    public CashRegisterAlreadyExistsByNumeralException(Integer numeral) {
        super("El numeral ingresado ya está siendo utilizado: " + numeral);
    }
}
