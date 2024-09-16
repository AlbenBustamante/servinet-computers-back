package com.servinetcomputers.api.domain.base;

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
}
