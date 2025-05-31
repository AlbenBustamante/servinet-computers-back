package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.user.application.usecase.GetJourneysUseCase;
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
    public List<JourneyDto> call(Integer userId, YearMonth month) {
        final var startDate = month.atDay(1).atStartOfDay();
        final var endDate = month.plusMonths(1).atDay(1).atStartOfDay();

        final var cashRegisterDetails = cashRegisterDetailPersistenceAdapter.getAllByUserIdBetween(userId, startDate, endDate);
        final List<JourneyDto> journeys = new ArrayList<>(cashRegisterDetails.size());

        for (final var detail : cashRegisterDetails) {
            final var discounts = expenseRepository.getAllByCashRegisterDetailIdAndDiscount(detail.getId(), true);
            var totalOfDiscounts = 0;

            for (final var discount : discounts) {
                totalOfDiscounts += discount.getValue();
            }

            final var finalWorking = getTime(detail.getFinalWorking());
            final var initialWorking = getTime(detail.getInitialWorking());
            final var finalBreak = getTime(detail.getFinalBreak());
            final var initialBreak = getTime(detail.getInitialBreak());

            final var differenceBetweenWorkingTimes = Duration.between(initialWorking, finalWorking);
            final var differenceBetweenBreakTimes = Duration.between(initialBreak, finalBreak);
            final var totalDuration = differenceBetweenWorkingTimes.minus(differenceBetweenBreakTimes);
            final var totalOfHours = String.format("%d:%02d", totalDuration.toHours(), totalDuration.toMinutesPart());

            final var journey = new JourneyDto(detail, discounts, totalOfDiscounts, totalOfHours);
            journeys.add(journey);
        }

        return journeys;
    }

    private LocalTime getTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return LocalTime.MIN;
        }

        return dateTime.toLocalTime();
    }
}
