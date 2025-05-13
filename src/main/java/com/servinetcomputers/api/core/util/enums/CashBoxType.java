package com.servinetcomputers.api.core.util.enums;

import lombok.Getter;

@Getter
public enum CashBoxType {
    CASH_REGISTER('C'),
    SAFE('S');

    private final Character type;

    CashBoxType(final Character type) {
        this.type = type;
    }

    public Character getType() {
        return type;
    }
}
