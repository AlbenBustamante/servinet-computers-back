package com.servinetcomputers.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This is the locale and currency configuration"
 */
@Configuration
public class LocaleConfig {

    @Bean
    public Locale locale() {
        return new Locale("es", "CO");
    }

    @Bean
    public NumberFormat numberFormat() {
        return NumberFormat.getCurrencyInstance(locale());
    }

}
