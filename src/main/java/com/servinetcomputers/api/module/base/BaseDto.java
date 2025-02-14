package com.servinetcomputers.api.module.base;

public record BaseDto(
        int hundredThousand,
        int fiftyThousand,
        int twentyThousand,
        int tenThousand,
        int fiveThousand,
        int twoThousand,
        int thousand,
        int fiveHundred,
        int twoHundred,
        int hundred,
        int fifty
) {
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

    public int calculateSafeBase() {
        return hundredThousand * 100 * 100000 +
                fiftyThousand * 100 * 50000 +
                twentyThousand * 100 * 20000 +
                tenThousand * 100 * 10000 +
                fiveThousand * 100 * 5000 +
                twoThousand * 100 * 2000 +
                thousand * 100 * 1000 +
                fiveHundred * 100 * 500 +
                twoHundred * 100 * 200 +
                hundred * 100 * 100 +
                fifty * 100 * 50;
    }
}
