package com.servinetcomputers.api.util.formatter.impl;

import com.servinetcomputers.api.util.formatter.ICurrencyFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * The {@link ICurrencyFormatter} implementation.
 */
@Component
@RequiredArgsConstructor
public class CurrencyFormatterImpl implements ICurrencyFormatter {

    private final NumberFormat numberFormat;

    @Override
    public String format(BigDecimal value) {
        return numberFormat.format(value);
    }

}
