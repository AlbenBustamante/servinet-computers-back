package com.servinetcomputers.api.domain.reports;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.domain.expense.persistence.JpaExpenseRepository;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformStatsDto;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformTransferRepository;
import com.servinetcomputers.api.domain.platform.persistence.mapper.PlatformBalanceMapper;
import com.servinetcomputers.api.domain.reports.abs.IReportsService;
import com.servinetcomputers.api.domain.reports.abs.ReportsMapper;
import com.servinetcomputers.api.domain.reports.dto.Reports;
import com.servinetcomputers.api.domain.reports.dto.ReportsResponse;
import com.servinetcomputers.api.domain.safes.persistence.JpaSafeRepository;
import com.servinetcomputers.api.domain.safes.persistence.mapper.SafeMapper;
import com.servinetcomputers.api.domain.transaction.persistence.JpaTransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Admin Dashboard Reports service implementation.
 */
@RequiredArgsConstructor
@Service
public class ReportsServiceImpl implements IReportsService {

    private final JpaExpenseRepository jpaExpenseRepository;
    private final JpaCashRegisterDetailRepository jpaCashRegisterDetailRepository;
    private final JpaPlatformBalanceRepository jpaPlatformBalanceRepository;
    private final JpaPlatformTransferRepository jpaPlatformTransferRepository;
    private final JpaSafeRepository jpaSafeRepository;
    private final JpaTransactionDetailRepository jpaTransactionDetailRepository;
    private final BaseMapper baseMapper;
    private final CashRegisterDetailMapper cashRegisterDetailMapper;
    private final PlatformBalanceMapper platformBalanceMapper;
    private final ReportsMapper reportsMapper;
    private final SafeMapper safeMapper;

    @Transactional(readOnly = true)
    @Override
    public ReportsResponse getReports(String code) {
        final var today = LocalDate.now();
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now());

        final var platformTransfers = jpaPlatformTransferRepository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate);
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
            final var transfersAmount = jpaPlatformTransferRepository.countByPlatformIdAndEnabledTrueAndCreatedDateBetween(platformId, startDate, endDate);
            final var totalTransfers = jpaPlatformTransferRepository.calculateTotalByPlatformIdAndCreatedDateBetween(platformId, startDate, endDate);

            final var transfersTotal = totalTransfers != null ? totalTransfers : 0;

            final var total = initialBalance + transfersTotal - finalBalance;

            platformsStats.add(new PlatformStatsDto(platformId, platformName, initialBalance, finalBalance, transfersAmount, transfersTotal, total));
        });

        return platformsStats;
    }

}
