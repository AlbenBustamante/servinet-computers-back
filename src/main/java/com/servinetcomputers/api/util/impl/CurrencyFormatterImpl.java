package com.servinetcomputers.api.util.impl;

import com.servinetcomputers.api.util.ICurrencyFormatter;
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
