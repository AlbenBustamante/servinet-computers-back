package com.servinetcomputers.api.domain.dashboard;

import com.servinetcomputers.api.domain.dashboard.abs.IDashboardService;
import com.servinetcomputers.api.domain.dashboard.dto.DashboardResponse;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.servinetcomputers.api.security.util.SecurityConstants.ADMIN_AUTHORITY;

/**
 * The Admin Dashboard Reports service implementation.
 */
@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements IDashboardService {

    private final PlatformBalanceRepository platformBalanceRepository;
    private final PlatformBalanceMapper platformBalanceMapper;

    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public DashboardResponse getDashboard() {
        final var startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        final var endDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        final var platformBalances = platformBalanceRepository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        final var totalPlatformBalances = platformBalanceRepository.calculateTotalByFinalBalanceAndCreatedDateBetween(startDate, endDate);
        final var totalBalance = totalPlatformBalances != null ? totalPlatformBalances : 0;

        return new DashboardResponse(totalBalance, platformBalanceMapper.toResponses(platformBalances));
    }
}
