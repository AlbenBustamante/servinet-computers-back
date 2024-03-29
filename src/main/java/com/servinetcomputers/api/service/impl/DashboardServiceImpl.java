package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.response.DashboardResponse;
import com.servinetcomputers.api.dto.response.DashboardResponse.PlatformDetailDashboardResponse;
import com.servinetcomputers.api.exception.UserNotFoundException;
import com.servinetcomputers.api.exception.UserUnavailableException;
import com.servinetcomputers.api.model.User;
import com.servinetcomputers.api.repository.TransferRepository;
import com.servinetcomputers.api.repository.UserRepository;
import com.servinetcomputers.api.service.IDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.util.constants.LocalConstants.DEFAULT_ZONE;
import static com.servinetcomputers.api.util.constants.SecurityConstants.USER_AUTHORITY;

/**
 * The Admin Dashboard Reports service implementation.
 */
@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements IDashboardService {

    private final UserRepository userRepository;
    private final TransferRepository transferRepository;

    @Secured(value = USER_AUTHORITY)
    @Override
    public DashboardResponse getReports(int userId) {
        final var user = getUser(userId);

        final var today = LocalDate.now(DEFAULT_ZONE);
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now(DEFAULT_ZONE));

        final List<PlatformDetailDashboardResponse> temporaryPlatforms = new ArrayList<>();
        var total = BigDecimal.ZERO;

        final List<PlatformDetailDashboardResponse> platforms = new ArrayList<>();

        temporaryPlatforms.forEach(platform -> {
            var platformValue = BigDecimal.ZERO;
            var transfersAmount = 0;

            final var alreadyExists = platforms.stream().anyMatch(p -> p.platformId() == platform.platformId());

            if (!alreadyExists) {
                platforms.add(new PlatformDetailDashboardResponse(
                        platform.platformId(), platform.platformName(), transfersAmount, platformValue + "")
                );
            }
        });

        return new DashboardResponse(total + "", temporaryPlatforms, platforms);
    }

    private User getUser(int userId) {
        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.getIsAvailable().equals(Boolean.FALSE)) {
            throw new UserUnavailableException(userId);
        }

        return user;
    }

}
