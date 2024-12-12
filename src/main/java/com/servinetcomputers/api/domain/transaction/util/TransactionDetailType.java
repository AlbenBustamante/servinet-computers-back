package com.servinetcomputers.api.domain.transaction.util;

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
