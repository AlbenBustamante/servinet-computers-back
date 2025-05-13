package com.servinetcomputers.api.core.util.enums;

public enum ChangeLogAction {
    UPDATE('U'),
    DELETE('D');

    private final Character type;

    ChangeLogAction(final Character type) {
        this.type = type;
    }

    public final Character getType() {
        return type;
    }
}
