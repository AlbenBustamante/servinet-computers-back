package com.servinetcomputers.api.domain.base;

import org.springframework.stereotype.Component;

@Component
public class BaseMapper {
    private static final String separator = ";";

    public BaseDto toDto(String base) {
        final var values = base.split(separator);

        return new BaseDto(
                Integer.parseInt(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2]),
                Integer.parseInt(values[3]),
                Integer.parseInt(values[4]),
                Integer.parseInt(values[5]),
                Integer.parseInt(values[6]),
                Integer.parseInt(values[7]),
                Integer.parseInt(values[8]),
                Integer.parseInt(values[9]),
                Integer.parseInt(values[10])
        );
    }

    public String toStr(BaseDto base) {
        return base.hundredThousand() + separator +
                base.fiftyThousand() + separator +
                base.twentyThousand() + separator +
                base.tenThousand() + separator +
                base.fiveThousand() + separator +
                base.twoThousand() + separator +
                base.thousand() + separator +
                base.fiveHundred() + separator +
                base.twoHundred() + separator +
                base.hundred() + separator +
                base.fifty();
    }

}
