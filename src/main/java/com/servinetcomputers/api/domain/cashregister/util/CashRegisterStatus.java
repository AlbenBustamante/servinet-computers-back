package com.servinetcomputers.api.domain.cashregister.util;

import lombok.Getter;

@Getter
public enum CashRegisterStatus {
    AVAILABLE('A'),
    OCCUPIED('O'),
    DISABLED('D');

    CashRegisterStatus(final Character status) {
        this.status = status;
    }

    private final Character status;
}
