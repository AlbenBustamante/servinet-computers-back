package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.module.user.application.usecase.GetJourneysUseCase;
import com.servinetcomputers.api.module.user.domain.dto.JourneyDetailDto;
import com.servinetcomputers.api.module.user.domain.dto.JourneyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GetJourneysService implements GetJourneysUseCase {
    private static final String JOURNEY_HOURS_FORMAT = "%02d:%02d:%02d";
    private static final String TOTAL_HOURS_FORMAT = "%02d:%02d:%02d";
    private final CashRegisterDetailRepository cashRegisterDetailRepository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public JourneyDetailDto call(Integer userId, YearMonth month) {
        final var startDate = month.atDay(1).atStartOfDay();
        final var endDate = month.plusMonths(1).atDay(1).atStartOfDay();

        final var cashRegisterDetails = cashRegisterDetailRepository.getAllByUserIdBetween(userId, startDate, endDate);
        final List<JourneyDto> journeys = new ArrayList<>(cashRegisterDetails.size());
        var totalOfDiscounts = 0;
        var totalOfSeconds = 0L;
        var totalOfTransactions = 0;

        for (final var detail : cashRegisterDetails) {
            final var discounts = expenseRepository.getAllByCashRegisterDetailIdAndDiscount(detail.getId(), true);
            var totalOfJourneyDiscounts = 0;

            for (final var discount : discounts) {
                totalOfJourneyDiscounts += discount.getValue();
            }

            totalOfDiscounts += totalOfJourneyDiscounts;

            final var finalWorking = getTime(detail.getFinalWorking());
            final var initialWorking = getTime(detail.getInitialWorking());
            final var finalBreak = getTime(detail.getFinalBreak());
            final var initialBreak = getTime(detail.getInitialBreak());

            final var differenceBetweenWorkingTimes = Duration.between(initialWorking, finalWorking);
            final var differenceBetweenBreakTimes = Duration.between(initialBreak, finalBreak);
            final var totalDuration = differenceBetweenWorkingTimes.minus(differenceBetweenBreakTimes);
            final var totalOfJourneyHours = String.format(JOURNEY_HOURS_FORMAT, totalDuration.toHours(), totalDuration.toMinutesPart(), totalDuration.toSecondsPart());

            final var totalOfJourneyTransactions = transactionDetailRepository.countByCashRegisterDetailId(detail.getId());
            totalOfTransactions += totalOfJourneyTransactions;

            final var journey = new JourneyDto(detail, discounts, totalOfJourneyDiscounts, totalOfJourneyTransactions, totalOfJourneyHours);
            journeys.add(journey);

            totalOfSeconds += totalDuration.toSeconds();
        }

        // https://www.inchcalculator.com/convert/second-to-hour/
        final var formattedTotalOfHours = getFormattedTotalOfHours((double) totalOfSeconds);

        return new JourneyDetailDto(formattedTotalOfHours, totalOfDiscounts, totalOfTransactions, journeys);
    }

    private static String getFormattedTotalOfHours(double totalOfSeconds) {
        final var hours = totalOfSeconds / 3600;
        final var wholeHours = String.valueOf(hours).split("\\.")[0];
        final var minutes = (hours - Integer.parseInt(wholeHours)) * 60;
        final var wholeMinutes = String.valueOf(minutes).split("\\.")[0];
        final var seconds = (minutes - Integer.parseInt(wholeMinutes)) * 60;
        final var wholeSeconds = String.valueOf(seconds).split("\\.")[0];

        final var hoursInt = Integer.parseInt(wholeHours);
        final var minutesInt = Integer.parseInt(wholeMinutes);
        final var secondsInt = Integer.parseInt(wholeSeconds);

        return String.format(TOTAL_HOURS_FORMAT, hoursInt, minutesInt, secondsInt, hoursInt, minutesInt, secondsInt);
    }

    private LocalTime getTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return LocalTime.MIN;
        }

        return dateTime.toLocalTime();
    }
}
