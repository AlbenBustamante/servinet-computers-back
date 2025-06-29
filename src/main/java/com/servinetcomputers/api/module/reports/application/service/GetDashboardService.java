package com.servinetcomputers.api.module.reports.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterRepository;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.platform.application.usecase.GetPlatformStatsByBalanceUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformStatsDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.module.reports.application.usecase.GetDashboardUseCase;
import com.servinetcomputers.api.module.reports.dto.DashboardResponse;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class GetDashboardService implements GetDashboardUseCase {
    private final CashRegisterDetailRepository cashRegisterDetailRepository;
    private final CashRegisterRepository cashRegisterRepository;
    private final ExpenseRepository expenseRepository;
    private final PlatformBalanceRepository platformBalanceRepository;
    private final SafeDetailRepository safeDetailRepository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final DateTimeService dateTimeService;
    private final GetPlatformStatsByBalanceUseCase getPlatformStatsByBalanceUseCase;

    /**
     * Get the admin reports of the day.
     *
     * @return a {@link DashboardResponse} with the results.
     */
    @Transactional(readOnly = true)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public DashboardResponse call(final LocalDate date) {
        final var newDate = date != null ? date : dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(newDate);
        final var endDate = dateTimeService.getMaxByDate(newDate);

        //final var platformBalancesTotal = platformBalanceRepository.calculateFinalBalanceBetween(startDate, endDate);
        final var platformBalances = platformBalanceRepository.getAllBetween(startDate, endDate);
        final List<PlatformStatsDto> platformsStats = new ArrayList<>(platformBalances.size());
        final var platformBalancesTotal = calculatePlatformBalancesTotal(platformBalances, platformsStats, newDate, startDate, endDate);

        final List<CashRegisterDetailDto> cashRegisterDetails;

        if (date == null) {
            final var cashRegisterIds = cashRegisterRepository.getAllIds();
            cashRegisterDetails = cashRegisterDetailRepository.getLatestWhereCashRegisterIdIsIn(cashRegisterIds);
        } else {
            cashRegisterDetails = cashRegisterDetailRepository.getAllBetween(startDate, endDate);
        }

        final var cashRegistersTotal = calculateCashRegistersTotal(cashRegisterDetails);

        final var safeDetails = safeDetailRepository.getAllByDateBetween(startDate, endDate);
        final var safesTotal = calculateSafesTotal(safeDetails);

        final var totalBalance = platformBalancesTotal + cashRegistersTotal + safesTotal;

        final var earnings = transactionDetailRepository.sumCommissionBetween(startDate, endDate);
        final var transactionsAmount = transactionDetailRepository.countBetween(startDate, endDate);
        final var expenses = expenseRepository.sumValuesBetween(startDate, endDate);

        return DashboardResponse.builder()
                .totalBalance(totalBalance)
                .earnings(earnings)
                .transactionsAmount(transactionsAmount)
                .expenses(expenses)
                .platformBalancesTotal(platformBalancesTotal)
                .cashRegistersTotal(cashRegistersTotal)
                .safesTotal(safesTotal)
                .platformsStats(platformsStats)
                .cashRegisterDetails(cashRegisterDetails)
                .safeDetails(safeDetails)
                .build();
    }

    private int calculatePlatformBalancesTotal(List<PlatformBalanceDto> platformBalances, List<PlatformStatsDto> platformsStats, LocalDate date, LocalDateTime startDate, LocalDateTime endDate) {
        if (platformBalances.isEmpty()) {
            return 0;
        }

        var platformBalancesTotal = 0;

        for (final var platform : platformBalances) {
            final var balance = platform.getFinalBalance() != null
                    ? platform.getFinalBalance()
                    : platform.getInitialBalance();

            platformBalancesTotal += balance;

            final var stats = getPlatformStatsByBalanceUseCase.call(platform, date);
            platformsStats.add(stats);
        }

        return platformBalancesTotal;
    }

    private int calculateCashRegistersTotal(List<CashRegisterDetailDto> cashRegisterDetails) {
        if (cashRegisterDetails.isEmpty()) {
            return 0;
        }

        var cashRegistersTotal = 0;

        for (final var detail : cashRegisterDetails) {
            final var base = detail.getFinalBase() != null
                    ? detail.getFinalBase()
                    : detail.getInitialBase();

            cashRegistersTotal += base;
        }

        return cashRegistersTotal;
    }

    private int calculateSafesTotal(List<SafeDetailDto> safeDetails) {
        if (safeDetails.isEmpty()) {
            return 0;
        }

        var safesTotal = 0;

        for (final var detail : safeDetails) {
            final var base = detail.getFinalBase() != null
                    ? detail.getFinalBase()
                    : detail.getInitialBase() != null ? detail.getInitialBase() : 0;

            safesTotal += base;
        }

        return safesTotal;
    }
}
