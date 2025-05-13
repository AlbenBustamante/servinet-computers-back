package com.servinetcomputers.api.module.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseDto {
    private int hundredThousand;
    private int fiftyThousand;
    private int twentyThousand;
    private int tenThousand;
    private int fiveThousand;
    private int twoThousand;
    private int thousand;
    private int fiveHundred;
    private int twoHundred;
    private int hundred;
    private int fifty;

    public static BaseDto zero() {
        return new BaseDto(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

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

    public BaseDto addOrSubtract(int amount, int denomination, boolean add) {
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
