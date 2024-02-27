package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The throwable exception when a balance is unavailable.
 */
@Getter
public class BalanceUnavailableException extends BalanceIdException {
    public BalanceUnavailableException(int balanceId) {
        super("The balance ID #" + balanceId + " is unavailable",
                HttpStatus.BAD_REQUEST,
                "BALANCE UNAVAILABLE",
                balanceId);
    }
}
