package com.servinetcomputers.api.domain.reports;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.expense.abs.ExpenseRepository;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.abs.PlatformTransferRepository;
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
        var totalPlatformBalances = platformBalanceRepository.calculateTotalByFinalBalanceAndCreatedDateBetween(startDate, endDate);

        totalPlatformBalances = totalPlatformBalances != null ? totalPlatformBalances : 0;
        totalBalance += totalPlatformBalances;

        final var finalBases = cashRegisterDetailRepository.findAllFinalBaseByCreatedDateBetweenAndEnabledTrue(startDate, endDate);

        for (final var finalBase : finalBases) {
            final var response = baseMapper.toDto(finalBase);
            totalBalance += response.calculate();
        }

        return new DashboardResponse(totalBalance, platformBalanceMapper.toResponses(platformBalances));
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

}
