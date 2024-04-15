package com.servinetcomputers.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.NumberFormat;
import java.time.ZoneId;
import java.util.Locale;

/**
 * Locale and currency configuration.
 */
@Configuration
public class LocaleConfig {
    private static final String LOCALE_LANGUAGE = "es";
    private static final String LOCALE_COUNTRY = "CO";
    private static final String LOCALE_ZONE = "America/Bogota";

    @Bean
    public ZoneId zoneId() {
        return ZoneId.of(LOCALE_ZONE);
    }

    @Bean
    public Locale locale() {
        return new Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY);
    }

    @Bean
    public NumberFormat numberFormat() {
        return NumberFormat.getInstance(locale());
    }

}
