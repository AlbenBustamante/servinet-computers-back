package com.servinetcomputers.api.domain.reports;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.domain.expense.persistence.JpaExpenseRepository;
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
import com.servinetcomputers.api.domain.safes.abs.SafeMapper;
import com.servinetcomputers.api.domain.safes.abs.SafeRepository;
import com.servinetcomputers.api.domain.transaction.abs.JpaTransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

/**
 * The Admin Dashboard Reports service implementation.
 */
@RequiredArgsConstructor
@Service
public class ReportsServiceImpl implements IReportsService {

    private final JpaExpenseRepository jpaExpenseRepository;
    private final JpaCashRegisterDetailRepository jpaCashRegisterDetailRepository;
    private final PlatformBalanceRepository platformBalanceRepository;
    private final PlatformTransferRepository platformTransferRepository;
    private final SafeRepository safeRepository;
    private final JpaTransactionDetailRepository jpaTransactionDetailRepository;
    private final BaseMapper baseMapper;
    private final CashRegisterDetailMapper cashRegisterDetailMapper;
    private final PlatformBalanceMapper platformBalanceMapper;
    private final ReportsMapper reportsMapper;
    private final SafeMapper safeMapper;

    @Transactional(readOnly = true)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public DashboardResponse getDashboard() {
        final var startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        final var endDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        final var platformBalances = platformBalanceRepository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        final var totalPlatformBalances = platformBalanceRepository.calculateTotalByFinalBalanceAndCreatedDateBetween(startDate, endDate);

        final var platformBalancesTotal = totalPlatformBalances != null ? totalPlatformBalances : 0;

        final var platformsStats = getPlatformsStats(platformBalanceMapper.toResponses(platformBalances), startDate, endDate);

        final var cashRegisterDetails = jpaCashRegisterDetailRepository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);

        var cashRegistersTotal = 0;

        for (final var cashRegisterDetail : cashRegisterDetails) {
            final var response = baseMapper.toDto(cashRegisterDetail.getFinalBase());
            final var base = response != null ? response.calculate() : 0;
            cashRegistersTotal += base;
        }

        final var safes = safeRepository.findAllByEnabledTrue();
        final var safesTotal = 0;

        /* for (final var safe : safes) {
            final var finalBase = baseMapper.toDto(safe.getFinalBase());
            safesTotal += finalBase.calculateSafeBase();
        } */

        final var totalBalance = platformBalancesTotal + cashRegistersTotal + safesTotal;

        return new DashboardResponse(
                totalBalance,
                platformsStats,
                platformBalancesTotal,
                cashRegisterDetailMapper.toResponses(cashRegisterDetails),
                cashRegistersTotal,
                safeMapper.toResponses(safes),
                safesTotal
        );
    }

    @Transactional(readOnly = true)
    @Override
    public ReportsResponse getReports(String code) {
        final var today = LocalDate.now();
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now());

        final var platformTransfers = platformTransferRepository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate);
        final var expenses = jpaExpenseRepository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, false);
        final var discounts = jpaExpenseRepository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, true);
        final var transactions = jpaTransactionDetailRepository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate);

        final var reports = new Reports(
                platformTransfers,
                expenses,
                discounts,
                transactions
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

            final var total = initialBalance + transfersTotal - finalBalance;

            platformsStats.add(new PlatformStatsDto(platformId, platformName, initialBalance, finalBalance, transfersAmount, transfersTotal, total));
        });

        return platformsStats;
    }

}
