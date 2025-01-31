package com.servinetcomputers.api.module.reports.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformStatsDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import com.servinetcomputers.api.module.reports.application.usecase.GetDashboardUseCase;
import com.servinetcomputers.api.module.reports.dto.DashboardResponse;
import com.servinetcomputers.api.module.safes.domain.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class GetDashboardService implements GetDashboardUseCase {
    private final PlatformBalanceRepository platformBalanceRepository;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;
    private final SafeRepository safeRepository;
    private final PlatformTransferRepository platformTransferRepository;
    private final DateTimeService dateTimeService;

    /**
     * Get the admin reports of the day.
     *
     * @return a {@link DashboardResponse} with the results.
     */
    @Transactional(readOnly = true)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public DashboardResponse call() {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final var platformBalances = platformBalanceRepository.getAllBetween(startDate, endDate);
        final var platformBalancesTotal = platformBalanceRepository.calculateFinalBalanceBetween(startDate, endDate);

        final var platformsStats = getPlatformsStats(platformBalances, startDate, endDate);

        final var cashRegisterDetails = cashRegisterDetailRepository.getAllBetween(startDate, endDate);

        var cashRegistersTotal = 0;

        for (final var cashRegisterDetail : cashRegisterDetails) {
            final var base = cashRegisterDetail.getDetailFinalBase() != null
                    ? cashRegisterDetail.getDetailFinalBase()
                    : cashRegisterDetail.getDetailInitialBase();

            cashRegistersTotal += base.calculate();
        }

        final var safes = safeRepository.getAll();
        final var safesTotal = 0;

        /* for (final var safe : safes) {
            final var finalBase = baseMapper.toDto(safe.getFinalBase());
            safesTotal += finalBase.calculateSafeBase();
        } */

        final var totalBalance = platformBalancesTotal + cashRegistersTotal + safesTotal;

        return new DashboardResponse(
                totalBalance,
                platformBalancesTotal,
                cashRegistersTotal,
                safesTotal,
                platformsStats,
                cashRegisterDetails,
                safes
        );
    }

    private List<PlatformStatsDto> getPlatformsStats(List<PlatformBalanceResponse> balances, LocalDateTime startDate, LocalDateTime endDate) {
        final List<PlatformStatsDto> platformsStats = new ArrayList<>(balances.size());

        balances.forEach(balance -> {
            final var platformId = balance.getPlatformId();
            final var platformName = balance.getPlatformName();
            final var initialBalance = balance.getInitialBalance();
            final var finalBalance = balance.getFinalBalance();
            final var transfersAmount = platformTransferRepository.getPlatformTransfersAmount(platformId, startDate, endDate);
            final var transfersTotal = platformTransferRepository.getPlatformTransfersTotal(platformId, startDate, endDate);

            final var total = initialBalance + transfersTotal - finalBalance;

            platformsStats.add(new PlatformStatsDto(platformId, platformName, initialBalance, finalBalance, transfersAmount, transfersTotal, total));
        });

        return platformsStats;
    }
}
