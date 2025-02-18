package com.servinetcomputers.api.module.reports.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformStatsDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import com.servinetcomputers.api.module.reports.application.usecase.GetDashboardUseCase;
import com.servinetcomputers.api.module.reports.dto.DashboardResponse;
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
    private final ExpenseRepository expenseRepository;
    private final PlatformBalanceRepository platformBalanceRepository;
    private final PlatformTransferRepository platformTransferRepository;
    private final SafeDetailRepository safeDetailRepository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final DateTimeService dateTimeService;

    /**
     * Get the admin reports of the day.
     *
     * @return a {@link DashboardResponse} with the results.
     */
    @Transactional(readOnly = true)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public DashboardResponse call(LocalDate date) {
        final var today = date != null ? date : dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.getMaxByDate(today);

        final var platformBalances = platformBalanceRepository.getAllBetween(startDate, endDate);
        //final var platformBalancesTotal = platformBalanceRepository.calculateFinalBalanceBetween(startDate, endDate);
        final List<PlatformStatsDto> platformsStats = new ArrayList<>(platformBalances.size());
        var platformBalancesTotal = 0;

        for (final var platform : platformBalances) {
            final var balance = platform.getFinalBalance() > 0
                    ? platform.getFinalBalance()
                    : platform.getInitialBalance();

            platformBalancesTotal += balance;
            platformsStats.add(getPlatformStats(platform, startDate, endDate));
        }

        final var cashRegisterDetails = cashRegisterDetailRepository.getAllBetween(startDate, endDate);
        var cashRegistersTotal = 0;

        for (final var cashRegisterDetail : cashRegisterDetails) {
            final var base = cashRegisterDetail.getDetailFinalBase() != null
                    ? cashRegisterDetail.getDetailFinalBase()
                    : cashRegisterDetail.getDetailInitialBase();

            cashRegistersTotal += base.calculate();
        }

        final var safeDetails = safeDetailRepository.getAllByDateBetween(startDate, endDate);
        var safesTotal = 0;

        for (final var safe : safeDetails) {
            final var base = safe.getDetailFinalBase() != null
                    ? safe.getDetailFinalBase()
                    : safe.getDetailInitialBase();

            safesTotal += base.calculateSafeBase();
        }

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

    private PlatformStatsDto getPlatformStats(PlatformBalanceResponse balance, LocalDateTime startDate, LocalDateTime endDate) {
        final var platformId = balance.getPlatformId();
        final var platformName = balance.getPlatformName();
        final var initialBalance = balance.getInitialBalance();
        final var finalBalance = balance.getFinalBalance();
        final var transfersAmount = platformTransferRepository.getPlatformTransfersAmount(platformId, startDate, endDate);
        final var transfersTotal = platformTransferRepository.getPlatformTransfersTotal(platformId, startDate, endDate);

        final var total = initialBalance + transfersTotal - finalBalance;

        return new PlatformStatsDto(platformId, platformName, initialBalance, finalBalance, transfersAmount, transfersTotal, total);
    }
}
