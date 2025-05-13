package com.servinetcomputers.api.module.base;

import org.springframework.stereotype.Component;

@Component
public class BaseMapper {
    private static final String separator = ";";

    public BaseDto toDto(String base) {
        final var values = base.split(separator);

        try {
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
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public String toStr(BaseDto base) {
        if (base == null) {
            return "";
        }

        return base.getHundredThousand() + separator +
                base.getFiftyThousand() + separator +
                base.getTwentyThousand() + separator +
                base.getTenThousand() + separator +
                base.getFiveThousand() + separator +
                base.getTwoThousand() + separator +
                base.getThousand() + separator +
                base.getFiveHundred() + separator +
                base.getTwoHundred() + separator +
                base.getHundred() + separator +
                base.getFifty();
    }

}
