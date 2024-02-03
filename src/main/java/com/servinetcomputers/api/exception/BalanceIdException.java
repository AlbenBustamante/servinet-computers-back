package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BalanceIdException extends AppException {
    private final int balanceId;

    protected BalanceIdException(String detail, HttpStatus status, String title, int balanceId) {
        super(detail, status, title);
        this.balanceId = balanceId;
    }
}
