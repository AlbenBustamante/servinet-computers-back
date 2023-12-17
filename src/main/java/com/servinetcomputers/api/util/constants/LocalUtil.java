package com.servinetcomputers.api.util.constants;

import java.time.ZoneId;

/**
 * The locale constants.
 */
public final class LocalUtil {
    public static final String LOCALE_LANGUAGE = "es";
    public static final String LOCALE_COUNTRY = "CO";
    public static final String LOCALE_ZONE = "America/Bogota";
    public static final ZoneId DEFAULT_ZONE = ZoneId.of(LOCALE_ZONE);

    private LocalUtil() {
    }
}
