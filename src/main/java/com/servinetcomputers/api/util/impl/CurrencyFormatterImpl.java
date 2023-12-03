package com.servinetcomputers.api.util.impl;

import com.servinetcomputers.api.util.ICurrencyFormatter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * The {@link ICurrencyFormatter} implementation.
 */
@Component
public class CurrencyFormatterImpl implements ICurrencyFormatter {

    @Override
    public String format(BigDecimal value) {
        final var numberFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        return numberFormat.format(value);
    }

}
