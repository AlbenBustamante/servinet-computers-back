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
        return hundredThousand +
                fiftyThousand +
                twentyThousand +
                tenThousand +
                fiveThousand +
                twoThousand +
                thousand +
                fiveHundred +
                twoHundred +
                hundred +
                fifty;
    }
}
