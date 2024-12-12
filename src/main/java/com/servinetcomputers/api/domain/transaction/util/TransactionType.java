package com.servinetcomputers.api.domain.transaction.util;

public enum TransactionType {
    NORMAL('N'),
    RECEIPT('R'),
    WALLET('W');

    private final Character type;

    TransactionType(final Character type) {
        this.type = type;
    }

    public final Character getType() {
        return type;
    }
}
