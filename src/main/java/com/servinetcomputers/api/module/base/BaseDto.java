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

    public BaseDto addOrSubtract(int amount, boolean add) {
        if (amount == 100000) {
            final var value = hundredThousand;
            hundredThousand = add ? value + 1 : value - 1;
        }

        if (amount == 50000) {
            final var value = fiftyThousand;
            fiftyThousand = add ? value + 1 : value - 1;
        }

        if (amount == 20000) {
            final var value = twentyThousand;
            twentyThousand = add ? value + 1 : value - 1;
        }

        if (amount == 10000) {
            final var value = tenThousand;
            tenThousand = add ? value + 1 : value - 1;
        }

        if (amount == 5000) {
            final var value = fiveThousand;
            fiveThousand = add ? value + 1 : value - 1;
        }

        if (amount == 2000) {
            final var value = twoThousand;
            twoThousand = add ? value + 1 : value - 1;
        }

        if (amount == 1000) {
            final var value = thousand;
            thousand = add ? value + 1 : value - 1;
        }

        if (amount == 500) {
            final var value = fiveHundred;
            fiveHundred = add ? value + 1 : value - 1;
        }

        if (amount == 200) {
            final var value = twoHundred;
            twoHundred = add ? value + 1 : value - 1;
        }

        if (amount == 100) {
            final var value = hundred;
            hundred = add ? value + 1 : value - 1;
        }

        if (amount == 50) {
            final var value = fifty;
            fifty = add ? value + 1 : value - 1;
        }

        return this;
    }
}
