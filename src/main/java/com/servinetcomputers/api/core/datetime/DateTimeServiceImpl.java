package com.servinetcomputers.api.core.datetime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class DateTimeServiceImpl implements DateTimeService {
    private final ZoneId zoneId;

    @Override
    public LocalDate dateNow() {
        return LocalDate.now(zoneId);
    }

    @Override
    public LocalTime timeOf(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return LocalTime.of(date.getHour(), date.getMinute(), date.getSecond());
    }

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now(zoneId);
    }

    @Override
    public LocalDateTime getMinByDate(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIN);
    }

    @Override
    public LocalDateTime getMaxByDate(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MAX);
    }

    @Override
    public LocalDateTime setCurrentDayToTime(LocalTime time) {
        return LocalDateTime.of(dateNow(), time);
    }

    @Override
    public LocalDateTime currentOf(LocalTime time) {
        return LocalDateTime.of(dateNow(), time);
    }

    @Override
    public String formattedNow() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(now());
    }
}
