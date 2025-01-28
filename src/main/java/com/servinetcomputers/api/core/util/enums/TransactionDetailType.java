package com.servinetcomputers.api.core.util.enums;

public enum TransactionDetailType {
    DEPOSIT('D'),
    WITHDRAWAL('W');

    TransactionDetailType(final Character type) {
        this.type = type;
    }

    private final Character type;

    public final Character getType() {
        return type;
    }
}
