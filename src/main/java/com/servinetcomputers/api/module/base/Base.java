package com.servinetcomputers.api.module.base;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Dominio para cono monetario de Colombia 2025.
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Base {
    /**
     * Cien mil - 100.000
     */
    private int hundredThousand;
    /**
     * Cincuenta mil - 50.000
     */
    private int fiftyThousand;
    /**
     * Veinte mil - 20.000
     */
    private int twentyThousand;
    /**
     * Diez mil - 10.000
     */
    private int tenThousand;
    /**
     * Cinco mil - 5.000
     */
    private int fiveThousand;
    /**
     * Dos mil - 2.000
     */
    private int twoThousand;
    /**
     * Mil - 1.000
     */
    private int thousand;
    /**
     * Quinientos - 500
     */
    private int fiveHundred;
    /**
     * Doscientos - 200
     */
    private int twoHundred;
    /**
     * Cien - 100
     */
    private int hundred;
    /**
     * Cincuenta - 50
     */
    private int fifty;

    /**
     * Todos los valores son cero.
     *
     * @return Base por defecto.
     */
    public static Base zero() {
        return new Base(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    /**
     * Producto de la cantidad de efectivo de una denominación por el valor de la denominación.
     *
     * @return la suma de todos los productos.
     */
    public int calculate() {
        return hundredThousand * 100000 +
                fiftyThousand * 50000 +
                twentyThousand * 20000 +
                tenThousand * 10000 +
                fiveThousand * 5000 +
                twoThousand * 2000 +
                thousand * 1000 +
                fiveHundred * 500 +
                twoHundred * 200 +
                hundred * 100 +
                fifty * 50;
    }

    /**
     * Suma o resta la cantidad de efectivo de una denominación.
     *
     * @param amount       cantidad de efectivo.
     * @param denomination denominación.
     * @param add          {@code true} si suma, en caso contrario, resta.
     * @return {@link Base} actualizada.
     */
    public Base addOrSubtract(int amount, int denomination, boolean add) {
        if (denomination == 100000) {
            final var value = hundredThousand;
            hundredThousand = add ? value + amount : value - amount;
        }

        if (denomination == 50000) {
            final var value = fiftyThousand;
            fiftyThousand = add ? value + amount : value - amount;
        }

        if (denomination == 20000) {
            final var value = twentyThousand;
            twentyThousand = add ? value + amount : value - amount;
        }

        if (denomination == 10000) {
            final var value = tenThousand;
            tenThousand = add ? value + amount : value - amount;
        }

        if (denomination == 5000) {
            final var value = fiveThousand;
            fiveThousand = add ? value + amount : value - amount;
        }

        if (denomination == 2000) {
            final var value = twoThousand;
            twoThousand = add ? value + amount : value - amount;
        }

        if (denomination == 1000) {
            final var value = thousand;
            thousand = add ? value + amount : value - amount;
        }

        if (denomination == 500) {
            final var value = fiveHundred;
            fiveHundred = add ? value + amount : value - amount;
        }

        if (denomination == 200) {
            final var value = twoHundred;
            twoHundred = add ? value + amount : value - amount;
        }

        if (denomination == 100) {
            final var value = hundred;
            hundred = add ? value + amount : value - amount;
        }

        if (denomination == 50) {
            final var value = fifty;
            fifty = add ? value + amount : value - amount;
        }

        return this;
    }
}
