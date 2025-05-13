package com.servinetcomputers.api.core.util.enums;

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
