package com.servinetcomputers.api.core.util.enums;

public enum ChangeLogType {
    TRANSACTION_DETAIL('T'),
    EXPENSE('E'),
    CASH_TRANSFER('C');

    private final Character type;

    ChangeLogType(final Character type) {
        this.type = type;
    }

    public final Character getType() {
        return type;
    }
}
