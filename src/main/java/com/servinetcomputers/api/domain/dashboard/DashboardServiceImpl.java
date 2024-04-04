package com.servinetcomputers.api.domain.dashboard;

import com.servinetcomputers.api.domain.dashboard.abs.IDashboardService;
import com.servinetcomputers.api.domain.dashboard.dto.DashboardResponse;
import com.servinetcomputers.api.domain.transfer.abs.TransferRepository;
import com.servinetcomputers.api.domain.user.abs.UserRepository;
import com.servinetcomputers.api.domain.user.User;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.security.util.SecurityConstants.CASHIER_AUTHORITY;
import static com.servinetcomputers.api.util.LocalConstants.DEFAULT_ZONE;

/**
 * The Admin Dashboard Reports service implementation.
 */
@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements IDashboardService {

    private final UserRepository userRepository;
    private final TransferRepository transferRepository;

    @Secured(value = CASHIER_AUTHORITY)
    @Override
    public DashboardResponse getReports(int userId) {
        final var user = getUser(userId);

        final var today = LocalDate.now(DEFAULT_ZONE);
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now(DEFAULT_ZONE));

        final List<DashboardResponse.PlatformDetailDashboardResponse> temporaryPlatforms = new ArrayList<>();
        var total = BigDecimal.ZERO;

        final List<DashboardResponse.PlatformDetailDashboardResponse> platforms = new ArrayList<>();

        temporaryPlatforms.forEach(platform -> {
            var platformValue = BigDecimal.ZERO;
            var transfersAmount = 0;

            final var alreadyExists = platforms.stream().anyMatch(p -> p.platformId() == platform.platformId());

            if (!alreadyExists) {
                platforms.add(new DashboardResponse.PlatformDetailDashboardResponse(
                        platform.platformId(), platform.platformName(), transfersAmount, platformValue + "")
                );
            }
        });

        return new DashboardResponse(total + "", temporaryPlatforms, platforms);
    }

    private User getUser(int userId) {
        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + userId));

        if (user.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Usuario no encontrado: " + userId);
        }

        return user;
    }

}
