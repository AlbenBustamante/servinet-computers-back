package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
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
    private final CashRegisterDetailPersistenceAdapter cashRegisterDetailPersistenceAdapter;
    private final ExpenseRepository expenseRepository;

    @Override
    public JourneyDetailDto call(Integer userId, YearMonth month) {
        final var startDate = month.atDay(1).atStartOfDay();
        final var endDate = month.plusMonths(1).atDay(1).atStartOfDay();

        final var cashRegisterDetails = cashRegisterDetailPersistenceAdapter.getAllByUserIdBetween(userId, startDate, endDate);
        final List<JourneyDto> journeys = new ArrayList<>(cashRegisterDetails.size());
        var totalOfDiscounts = 0;
        var seconds = 0L;

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
            final var totalOfJourneyHours = String.format("%02d:%02d", totalDuration.toHours(), totalDuration.toMinutesPart());

            final var journey = new JourneyDto(detail, discounts, totalOfJourneyDiscounts, totalOfJourneyHours);
            journeys.add(journey);

            seconds += totalDuration.toSeconds();
        }

        // https://www.inchcalculator.com/convert/second-to-hour/
        final var hours = (double) seconds / 3600;
        final var wholeHours = String.valueOf(hours).split("\\.")[0];
        final var minutes = (hours - Integer.parseInt(wholeHours)) * 60;
        final var wholeMinutes = String.valueOf(minutes).split("\\.")[0];

        final var formattedTotalOfHours = String.format("%02d:%02d", Integer.parseInt(wholeHours), Integer.parseInt(wholeMinutes));

        return new JourneyDetailDto(formattedTotalOfHours, totalOfDiscounts, journeys);
    }

    private LocalTime getTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return LocalTime.MIN;
        }

        return dateTime.toLocalTime();
    }
}
