package com.servinetcomputers.api.domain.reports;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.expense.abs.ExpenseRepository;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.abs.PlatformTransferRepository;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.dto.PlatformStatsDto;
import com.servinetcomputers.api.domain.reports.abs.IReportsService;
import com.servinetcomputers.api.domain.reports.abs.ReportsMapper;
import com.servinetcomputers.api.domain.reports.dto.DashboardResponse;
import com.servinetcomputers.api.domain.reports.dto.Reports;
import com.servinetcomputers.api.domain.reports.dto.ReportsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.security.util.SecurityConstants.ADMIN_AUTHORITY;

/**
 * The Admin Dashboard Reports service implementation.
 */
@RequiredArgsConstructor
@Service
public class ReportsServiceImpl implements IReportsService {

    private final ExpenseRepository expenseRepository;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;
    private final PlatformBalanceRepository platformBalanceRepository;
    private final PlatformTransferRepository platformTransferRepository;
    private final BaseMapper baseMapper;
    private final PlatformBalanceMapper platformBalanceMapper;
    private final ReportsMapper reportsMapper;

    @Transactional(readOnly = true)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public DashboardResponse getDashboard() {
        final var startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        final var endDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        var totalBalance = 0;

        final var platformBalances = platformBalanceRepository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        final var totalPlatformBalances = platformBalanceRepository.calculateTotalByFinalBalanceAndCreatedDateBetween(startDate, endDate);

        final var platformBalancesTotal = totalPlatformBalances != null ? totalPlatformBalances : 0;
        totalBalance += platformBalancesTotal;

        final var platformsStats = getPlatformsStats(platformBalanceMapper.toResponses(platformBalances), startDate, endDate);

        final var finalBases = cashRegisterDetailRepository.findAllFinalBaseByCreatedDateBetweenAndEnabledTrue(startDate, endDate);

        for (final var finalBase : finalBases) {
            final var response = baseMapper.toDto(finalBase);
            totalBalance += response != null ? response.calculate() : 0;
        }

        return new DashboardResponse(
                totalBalance,
                platformsStats,
                platformBalancesTotal
        );
    }

    @Transactional(readOnly = true)
    @Override
    public ReportsResponse getReports(String code) {
        final var startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        final var endDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        final var platformTransfers = platformTransferRepository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate);
        final var expenses = expenseRepository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, false);
        final var discounts = expenseRepository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, true);

        final var reports = new Reports(
                platformTransfers,
                expenses,
                discounts
        );

        return reportsMapper.toResponse(reports);
    }

    private List<PlatformStatsDto> getPlatformsStats(List<PlatformBalanceResponse> balances, LocalDateTime startDate, LocalDateTime endDate) {
        final List<PlatformStatsDto> platformsStats = new ArrayList<>(balances.size());

        balances.forEach(balance -> {
            final var platformId = balance.getPlatformId();
            final var platformName = balance.getPlatformName();
            final var initialBalance = balance.getInitialBalance();
            final var finalBalance = balance.getFinalBalance();
            final var transfersAmount = platformTransferRepository.countByPlatformIdAndEnabledTrueAndCreatedDateBetween(platformId, startDate, endDate);
            final var totalTransfers = platformTransferRepository.calculateTotalByPlatformIdAndCreatedDateBetween(platformId, startDate, endDate);

            final var transfersTotal = totalTransfers != null ? totalTransfers : 0;

            final var total = finalBalance - initialBalance + transfersTotal;

            platformsStats.add(new PlatformStatsDto(platformId, platformName, initialBalance, finalBalance, transfersAmount, transfersTotal, total));
        });

        return platformsStats;
    }

}
