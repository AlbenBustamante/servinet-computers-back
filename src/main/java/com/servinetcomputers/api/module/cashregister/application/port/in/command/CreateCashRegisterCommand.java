package com.servinetcomputers.api.module.cashregister.application.port.in.command;

import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;

/**
 * Comando para registrar una nueva caja registradora.
 *
 * @param numeral     numeral.
 * @param description descripción.
 * @param status      estado.
 */
public record CreateCashRegisterCommand(
        int numeral,
        String description,
        CashRegisterStatus status
) {
}
