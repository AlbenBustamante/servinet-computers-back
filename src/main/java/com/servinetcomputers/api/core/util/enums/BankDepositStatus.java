package com.servinetcomputers.api.core.util.enums;

public enum BankDepositStatus {
    OPEN('O'),
    IN_PROGRESS('P'),
    CLOSED('C');
    
    private final Character status;

    BankDepositStatus(final Character status) {
        this.status = status;
    }

    public final Character getStatus() {
        return status;
    }
}
