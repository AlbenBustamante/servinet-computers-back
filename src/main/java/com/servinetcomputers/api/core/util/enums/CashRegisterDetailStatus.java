package com.servinetcomputers.api.core.util.enums;

public enum CashRegisterDetailStatus {
    WORKING('W'),
    RESTING('R'),
    CLOSED('C');

    private final Character status;

    CashRegisterDetailStatus(final Character status) {
        this.status = status;
    }

    public final Character getStatus() {
        return status;
    }
}
