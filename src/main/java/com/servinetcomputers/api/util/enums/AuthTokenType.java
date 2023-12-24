package com.servinetcomputers.api.util.enums;

/**
 * The authentication Json Web Token type.
 */
public enum AuthTokenType {
    USER("u"),
    CAMPUS("c");

    AuthTokenType(final String value) {
        this.value = value;
    }

    private final String value;

    @Override
    public String toString() {
        return this.value;
    }
}
