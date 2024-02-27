package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

/**
 * The throwable exception when a balance is not found by the ID.
 */
public class BalanceNotFoundException extends BalanceIdException {

    public BalanceNotFoundException(int balanceId) {
        super("Balance ID #" + balanceId + " is not found",
                HttpStatus.NOT_FOUND,
                "BALANCE NOT FOUND",
                balanceId);
    }

}
