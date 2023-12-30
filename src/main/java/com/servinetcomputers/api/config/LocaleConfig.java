package com.servinetcomputers.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.NumberFormat;
import java.util.Locale;

import static com.servinetcomputers.api.util.constants.LocalConstants.LOCALE_COUNTRY;
import static com.servinetcomputers.api.util.constants.LocalConstants.LOCALE_LANGUAGE;

/**
 * Locale and currency configuration.
 */
@Configuration
public class LocaleConfig {

    @Bean
    public Locale locale() {
        return new Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY);
    }

    @Bean
    public NumberFormat numberFormat() {
        return NumberFormat.getInstance(locale());
    }

}
