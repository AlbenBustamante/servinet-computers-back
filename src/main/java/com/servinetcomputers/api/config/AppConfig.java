package com.servinetcomputers.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Here will be the main beans for the application.
 */
@Configuration
public class AppConfig {

    @Bean
    public Locale locale() {
        return new Locale("es", "CO");
    }

    @Bean
    public NumberFormat numberFormat() {
        return NumberFormat.getCurrencyInstance(locale());
    }

}
