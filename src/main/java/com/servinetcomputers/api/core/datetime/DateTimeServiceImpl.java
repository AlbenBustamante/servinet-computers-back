package com.servinetcomputers.api.core.datetime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@Component
public class DateTimeServiceImpl implements DateTimeService {
    private final ZoneId zoneId;

    @Override
    public LocalDate dateNow() {
        return LocalDate.now(zoneId);
    }

    @Override
    public LocalTime timeNow() {
        return LocalTime.now(zoneId);
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
        return LocalDateTime.of(date, timeNow());
    }
}
