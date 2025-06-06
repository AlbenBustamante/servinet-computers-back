package com.servinetcomputers.api.core.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface DateTimeService {
    LocalDate dateNow();

    LocalTime timeOf(LocalDateTime date);

    LocalDateTime now();

    LocalDateTime getMinByDate(LocalDate date);

    LocalDateTime getMaxByDate(LocalDate date);

    LocalDateTime setCurrentDayToTime(LocalTime time);

    LocalDateTime currentOf(LocalTime time);

    String formattedNow();
}
