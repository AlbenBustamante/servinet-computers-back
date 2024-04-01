package com.servinetcomputers.api.util;

import java.time.ZoneId;

/**
 * The locale constants.
 */
public final class LocalConstants {
    public static final String LOCALE_LANGUAGE = "es";
    public static final String LOCALE_COUNTRY = "CO";
    public static final String LOCALE_ZONE = "America/Bogota";
    public static final ZoneId DEFAULT_ZONE = ZoneId.of(LOCALE_ZONE);

    private LocalConstants() {
    }
}
