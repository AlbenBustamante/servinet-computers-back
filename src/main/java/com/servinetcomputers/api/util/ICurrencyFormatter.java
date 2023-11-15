package com.servinetcomputers.api.util;

import java.math.BigDecimal;

/**
 * Map a value with number format to the local format.
 */
public interface ICurrencyFormatter {

    /**
     * Map a BigDecimal value to the local format.
     *
     * @param value the value to format.
     * @return the formatted value.
     */
    String format(BigDecimal value);

}
