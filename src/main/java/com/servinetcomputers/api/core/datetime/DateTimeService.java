package com.servinetcomputers.api.core.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface DateTimeService {
    LocalDate dateNow();

    LocalTime timeNow();

    LocalDateTime now();

    LocalDateTime getMinByDate(LocalDate date);

    LocalDateTime getMaxByDate(LocalDate date);
}
